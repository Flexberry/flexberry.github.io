---
title: Flexberry Security
keywords: flexberry, security
sidebar: flexberry-security_sidebar
toc: false
permalink: ru/fs_permissions_structure.html
lang: ru
---

## Устройство системы полномочий

### Основные объекты

Агенты (Agent): пользователи, роли, группы. Пользователь входит в роли, роли также могут входить в роли. Иерархия ролей динамически настраивается. Группы используются, когда у пользователей одинаковые логины, тогда можно развести их с помощью групп.
Пользователей может заводить администратор, они могут автоматически создаваться сервером, подтягиваться с помощью active directory и т.д.

Субъекты полномочий (Subject): типы данных (классы), операции. Операция – строка, любое имя. Обычно операции влияют на доступность кнопок.

Разрешения (Permission) - объект, куда добавляются ссылки на агента, которому выдаются права, и на объект, к которому выдаются права (тип данных, операция).

Доступ (Access) - сюда добавляется ссылка на разрешение и устанавливается уровень доступа: Full, Read, Delete, Update.

### Возможности установления полномочий

- На уровне типов
- На уровне строк, атрибутов (через фильтр LangDef)
- Ограничение по операциям

### Интерфейс полномочий

Для работы полномочий ORM нужна имплементация интерфейса ISecurityManager, который содержит:
- Права на объекты
- Права на атрибуты
- Проверка полномочий на выполнение операции
- Функция получения ограничения
- Проверка существующего логина
- Проверка доступа на атрибут

EmptySecurityManager – имплементация интерфейса по умолчанию без проверки полномочий.

{% include note.html content="Нет запрещающих полномочий. По умолчанию все закрыто, нужно давать доступ к каждому объекту отдельно" %}

### Пример кастомной имплементации интерфейса ISecurityManager

``` csharp
namespace App.Security
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Text.RegularExpressions;
    using ICSSoft.STORMNET;
    using ICSSoft.STORMNET.Business;
    using ICSSoft.STORMNET.FunctionalLanguage;
    using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
    using ICSSoft.STORMNET.Security;
    using ICSSoft.STORMNET.Windows.Forms;
    using NewPlatform.Flexberry.Caching;
    using UnauthorizedAccessException = ICSSoft.STORMNET.UnauthorizedAccessException;

    public class SecurityManager : ISecurityManager
    {
        /// <summary>
        /// Определение языка ограничений для конструирования ограничивающих функций.
        /// </summary>
        /// <remarks>
        /// Использует сервис данных менеджера полномочий.
        /// При создании функций ограничения следует использовать именно данную инстанцию,
        /// а не статическое свойство <c>ExternalLangDef.LanguageDef</c>.
        /// </remarks>
        protected readonly ExternalLangDef LangDef;

        /// <summary>
        /// Сервис данных, использующийся для загрузки объектов полномочий.
        /// </summary>
        private readonly IDataService dataService;

        /// <summary>
        /// Кеш для данной инстанции <see cref="SecurityManager"/>.
        /// </summary>
        private readonly ICacheService cacheService;

        public SecurityManager(IDataService dataService, ICacheService cacheService)
        {
            if (dataService == null)
                throw new ArgumentNullException(nameof(dataService));

            this.dataService = dataService;
            this.cacheService = cacheService;
            LangDef = new ExternalLangDef { DataService = dataService };
        }

        /// <summary>
        /// Флаг включенных полномочий.
        /// </summary>
        /// <remarks>
        /// В данной реализации не используется.
        /// </remarks>
        public bool Enabled { get; }

        /// <summary>
        /// Флаг включенных полномочий над объектами.
        /// </summary>
        /// <remarks>
        /// В данной реализации не используется.
        /// </remarks>
        public bool UseRightsOnObjects { get; }

        /// <summary>
        /// Флаг включенных полномочий над атрибутами.
        /// </summary>
        /// <remarks>
        /// В данной реализации не используется.
        /// </remarks>
        public bool UseRightsOnAttribute { get; }

        /// <summary>
        /// Регулярное выражение для извлечения информации о контроле прав на атрибуты из <see cref="DataServiceExpressionAttribute"/>.
        /// </summary>
        public string AttributeCheckExpressionPattern => @"/\*Operation:(?<Operation>.*);DeniedAccessValue:(?<DeniedAccessValue>.*)\*/";

        /// <summary>
        /// Проверка полномочий на выполнение операции.
        /// </summary>
        /// <remarks>Проверяется только наличие записи, но не тип доступа.</remarks>
        /// <param name="operationId">Идентификатор операции.</param>
        /// <returns>
        /// Если у текущего пользователя есть доступ, то <c>true</c>.
        /// </returns>
        public bool AccessCheck(int operationId)
        {
            return AccessCheck(operationId.ToString());
        }

        /// <summary>
        /// Проверка полномочий на выполнение операции с типом.
        /// </summary>
        /// <param name="type">Тип объекта данных.</param>
        /// <param name="operation">Тип операции.</param>
        /// <param name="throwException">Генерировать ли исключение в случае отсутствия прав.</param>
        /// <returns>
        /// Если у текущего пользователя есть доступ, то <c>true</c>.
        /// </returns>
        /// <exception cref="ArgumentNullException">Исключение генерируется при передаче <c>null</c> в качестве значения для <paramref name="type"/>.</exception>
        /// <exception cref="ICSSoft.STORMNET.UnauthorizedAccessException">
        /// Исключение генерируется в том случае, если у пользователя отсутствует доступ и параметр <paramref name="throwException"/> установлен в <c>true</c>.
        /// </exception>
        public bool AccessObjectCheck(Type type, tTypeAccess operation, bool throwException)
        {            
            bool result = false;
            
            var lfSubject = LangDef.GetFunction(LangDef.funcEQ, new VariableDef(LangDef.StringType, Information.ExtractPropertyPath<Permition>(x => x.Subject.Name)), type.FullName);

            var dvd = new DetailVariableDef
            {
                ConnectMasterPorp = Information.ExtractPropertyPath<Access>(x => x.Permition),
                OwnerConnectProp = new[] { SQLWhereLanguageDef.StormMainObjectKey },
                View = Access.Views.CheckAccessOnObject,
                Type = LangDef.DetailsType
            };

            var lfTypeAccess = LangDef.GetFunction(LangDef.funcEQ, new VariableDef(LangDef.StringType, Information.ExtractPropertyName<Access>(x => x.TypeAccess)), EnumCaption.GetCaptionFor(operation));

            var lf = LangDef.GetFunction(
                    LangDef.funcOR,
                    lfTypeAccess);

            var lfOperation = LangDef.GetFunction(LangDef.funcExist, dvd, lf);

            var fullLf = LangDef.GetFunction(LangDef.funcAND, lfSubject, lfOperation);

            List<Permition> permits = GetPermitions(Permition.Views.CheckAccessOnObject, fullLf);

            result = permits.Any();                

            if (!result && throwException)
                throw new UnauthorizedAccessException(operation.ToString(), type);

            return result;
        }

        /// <summary>
        /// Проверка полномочий на выполнение операции с объектом.
        /// </summary>
        /// <param name="obj">Объект данных.</param>
        /// <param name="operation">Тип операции.</param>
        /// <param name="throwException">Генерировать ли исключение в случае отсутствия прав.</param>
        /// <returns>Если у текущего пользователя есть доступ, то <c>true</c>.</returns>
        /// <exception cref="ArgumentNullException">Исключение генерируется при передаче <c>null</c> в качестве значения для <paramref name="obj"/>.</exception>
        /// <exception cref="ICSSoft.STORMNET.UnauthorizedAccessException">
        /// Исключение генерируется в том случае, если у пользователя отсутствует доступ и параметр <paramref name="throwException"/> установлен в <c>true</c>.
        /// </exception>
        public bool AccessObjectCheck(object obj, tTypeAccess operation, bool throwException)
        {
            if (obj == null)
                throw new ArgumentNullException(nameof(obj));

            // Проверяем полномочия на тип.
            Type type = obj.GetType();

            bool result = AccessObjectCheck(type, operation, throwException);
            return result;
        }

        /// <summary>
        /// Проверить наличие в системе логина (чувствительность к регистру зависит от настроек источника данных).
        /// Уникальность проверяется без контроля доменов, то есть гарантируется уникальность в рамках всей таблицы.
        /// Отключенные пользователи тоже учитываются, как занимающие логин.
        /// </summary>
        /// <param name="login">Логин, который проверяем.</param>
        /// <returns>Если логин свободен, то <see cref="OperationResult.ЛогинСвободен"/>, если занят, то <see cref="OperationResult.ЛогинЗанят"/>.</returns>
        /// <exception cref="ArgumentException">Исключение генерируется при передаче <c>null</c> или <c>string.Empty</c> в качестве значения для <paramref name="login"/>.</exception>
        public OperationResult CheckExistLogin(string login)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Метод проверки прав на доступ текущего пользователя к операции, заданной в <see cref="DataServiceExpressionAttribute"/> атрибута.
        /// </summary>
        /// <param name="expression">Строка <see cref="DataServiceExpressionAttribute"/>.</param>
        /// <param name="deniedAccessValue">Значение, которое должен получить атрибут при отсутствии прав.</param>
        /// <returns>Если у текущего пользователя есть доступ, то <c>true</c>.</returns>
        public bool CheckAccessToAttribute(string expression, out string deniedAccessValue)
        {
            deniedAccessValue = string.Empty;

            if (string.IsNullOrEmpty(expression))
                return true;

            Match match = Regex.Match(expression, AttributeCheckExpressionPattern);

            if (match.Success)
            {
                string operation = match.Groups["Operation"].Value;
                deniedAccessValue = match.Groups["DeniedAccessValue"].Value;

                // Проверка полномочий на атрибуты объекта объекты.
                // Доступ есть, если есть права на операцию.
                return AccessCheck(operation);
            }

            // Если не удалось распознать выражение для контроля прав, считаем, что права есть.
            return true;
        }

        /// <summary>
        /// Проверка полномочий на выполнение операции.
        /// </summary>
        /// <remarks>Проверяется только наличие записи, но не тип доступа.</remarks>
        /// <param name="operationId">Идентификатор операции.</param>
        /// <returns>
        /// Если у текущего пользователя есть доступ, то <c>true</c>.
        /// </returns>
        public bool AccessCheck(string operationId)
        {
            bool result;
            
            Function lfSubject = LangDef.GetFunction(LangDef.funcEQ, new VariableDef(LangDef.StringType, Information.ExtractPropertyPath<Permition>(x => x.Subject.Name)), operationId);
            List<Permition> permits = GetPermitions(Permition.Views.CheckAccessOperation, lfSubject);
            result = permits.Any();

            return result;
            
        }

        /// <summary>
        /// Получить список разрешений для ролей.
        /// </summary>
        /// <param name="view">Вью для lcs.</param>
        /// <param name="limitFunction">Лимит-функция со значениями ролей.</param>
        private List<Permition> GetPermitions(ICSSoft.STORMNET.View view, Function limitFunction)
        {
            List<string> allAgentPKs = new List<string>();
            string[] agentKeys = allAgentPKs.ToArray();

            object permitDef = new VariableDef(LangDef.GuidType, Information.ExtractPropertyPath<Permition>(x => x.Agent));

            object[] funcInParams = new[] { permitDef }.Concat(agentKeys).ToArray();
            Function lfAgents = LangDef.GetFunction(LangDef.funcIN, funcInParams);

            LoadingCustomizationStruct lcsPermit = LoadingCustomizationStruct.GetSimpleStruct(typeof(Permition), Permition.Views.CheckAccessOnObject);

            lcsPermit.LimitFunction = LangDef.GetFunction(
                   LangDef.funcAND,
                   lfAgents,
                   limitFunction);

            List<Permition> result = new List<Permition>();
            DataObject[] permitionDataObjectsArray = dataService.LoadObjects(lcsPermit);

            foreach (DataObject permitionObject in permitionDataObjectsArray)
            {
                result.Add((permitionObject as Permition));
            }

            return result;
        }


        /// <summary>
        /// Получить ограничение для текущего пользователя.
        /// </summary>
        /// <remarks>
        /// Не поддерживается в текущей реализации.
        /// </remarks>
        /// <param name="subjectType">Тип объекта.</param>
        /// <param name="operation">Тип операции.</param>
        /// <param name="limit">Ограничение, которое есть для текущего пользователя.</param>
        /// <param name="canAccess">Есть ли доступ к этому типу у пользователя.</param>
        /// <returns>Результат выполнения операции.</returns>
        public OperationResult GetLimitForAccess(Type subjectType, tTypeAccess operation, out object limit, out bool canAccess)
        {
            canAccess = AccessObjectCheck(subjectType, operation, false);
            limit = null;

            return OperationResult.Успешно;
        }

        /// <summary>
        /// Получить роли с заданными ограничениями, которые реализуют функцию разграничения по объектам.
        /// </summary>
        /// <remarks>
        /// Не поддерживается в текущей реализации.
        /// </remarks>
        /// <param name="type">Класс, для которого получаем ограничения.</param>
        /// <param name="rolesWithAccesses">Роли с заданными ограничениями для этих ролей.</param>
        /// <returns>Результат выполнения операции.</returns>
        public OperationResult GetLimitStrForRoles(Type type, out List<RoleWithAccesses> rolesWithAccesses)
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Задать ограничение для указанной роли.
        /// </summary>
        /// <remarks>
        /// Не поддерживается в текущей реализации.
        /// </remarks>
        /// <param name="type">Тип объектов данных, для которых будет применяться данный фильтр.</param>
        /// <param name="operation">Тип доступа, для которого применяется этот фильтр.</param>
        /// <param name="roleName">Название роли.</param>
        /// <param name="filter">Сериализованный фильтр, который будет применяться для указанной роли.</param>
        /// <returns>Результат выполнения операции.</returns>
        public OperationResult SetLimitStrForRole(Type type, tTypeAccess operation, string roleName, string filter)
        {
            throw new NotImplementedException();
        }
    }

}

```
