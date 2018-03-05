---
title: Создание собственных функций при использовании LanguageDef
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения
summary: Разработка языка задания ограничений
toc: true
permalink: ru/fo_using-languagedef.html
lang: ru
---

Существуют стандартные "построители" функций для наложения ограничений, [SQLWhereLanguageDef](fo_function-list.html) и [ExternalLangDef](fo_external-lang-def.html). 
Создание собственных функций при использовании LanguageDef (языка задания ограничений) позволяет создавать СУБД-независимые конструкции и использовать из для ограничения через [FunctionalLanguage](fo_limit-function.html) или рукописные скрипты. Использование этого подхода позволяет создавать более лёгкий в сопровождении код, нежели написание дублирующих выражений для каждой СУБД.

{% include important.html content="Пользовательские функции должны начинаться с символов `user_`!. "%}

### Пример

Разработка языка задания ограничений ExportLanguage, работа с которым будет производиться следующим образом:

```csharp
// Создание экземпляра языка.
ExportLanguage langForExport = new ExportLanguage(this.DataService);
ICSSoft.STORMNET.Windows.Forms.ExternalLangDef myLangDef = langForExport.Language;
myLangDef.GetFunction("user_LPAD_SPACE", langForExport.q("Т.НомерКУСП"), 8)
myLangDef.GetFunction("user_Day", langForExport.q("Т.ВремяЗаполнения"))
```

Класс `ExternalLangDef` не позволяет в одном экземпляре содержать предопределённые функции и заданные руками, поэтому приходится использовать 2 экземпляра языка (экземпляры ничем не отличаются, за исключением функций, которые используют для записи выражений):

* new ExternalLangDef()
* (new ExportLanguage()).Language. 

`Функция q` носит вспомогательный характер, и не является частью подхода. Она, для удобства создания кода большого объёма, разделяет идентификатор, содержащий точки, на несколько идентификаторов, идущих через точку.

```csharp
using ICSSoft.STORMNET.UI;
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
using System.Collections;
  
public class ExportLanguage
{
    /// <summary>
    /// DataService для конкретной СУБД.
    /// </summary>
    private IDataService DataService;
     
    /// <summary>
    /// Конструктор, котрый требует определения сервиса конкретной СУБД.
    /// </summary>
    /// <param name="dataService"></param>
    public ExportLanguage(IDataService dataService)
    {
        DataService = dataService;
    }
     
    /// <summary>
    /// Создаёт экземпляр языка, содержащего заданные функции (и только их)
    /// </summary>
    public ICSSoft.STORMNET.Windows.Forms.ExternalLangDef Language
    {
        get
        {
            ICSSoft.STORMNET.Windows.Forms.ExternalLangDef myLangDef = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef();
            TuneUp_Language(myLangDef);
            return myLangDef;
        }
    }
     
    /// <summary>
    /// Настройка языка (при этом стандартные возможности отбрасываются)
    /// </summary>
    /// <param name="langdef">Экземпляр языка для настройки</param>
    public void TuneUp_Language(ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef)
    {
        langdef.UserSQLTranslFunction = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef.delegateUserSQLTranslFunction(SQLTranslFunction);
        CreateNewLimitFunctions(langdef);
    }
     
    /// <summary>
    /// Определение функций языка (стандартные функции станут невозможными)
    /// </summary>
    /// <param name="langdef"></param>
    public void CreateNewLimitFunctions(ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef)
    {
        string funcName = string.Empty;
        FunctionDef funcDef = null;
         
        // для получения FuncID
        // FuncID - идентификатор функции в языке.
        // Стандартные функции имеют номера, начиная с единицы, и увеличиваются в порядке возрастнания числа.
        // Поэтому, чтобы не использовать занятые номера, будет отсчитывать сверху вниз от максимального значения.
        int i = int.MaxValue - 1;
        ArrayList ar;
         
        #region CHARINDEX
            ar = new ArrayList();
            funcName = "user_CHARINDEX";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            "Позиция символа в строке",
                            null,
                            "(Позиция символа ({0}) в строке {1})",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType),
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region LTRIM
            ar = new ArrayList();
            funcName = "user_LTRIM";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.StringType,
                            funcName,
                            "Отсечение пробелов слева",
                            null,
                            "(Отсечение пробелов слева({0}))",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region LEFT
            ar = new ArrayList();
            funcName = "user_LEFT";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.StringType,
                            funcName,
                            "Урезание длины строки",
                            null,
                            "(Урезание длины строки({0}))",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region LEN
            ar = new ArrayList();
            funcName = "user_LEN";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            "Длина строки",
                            null,
                            "(Длина строки({0}))",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region CAST_AS_NUMERIC
            ar = new ArrayList();
            funcName = "user_CAST_AS_NUMERIC";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            string.Empty,
                            null,
                            string.Empty,
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region Day
            ar = new ArrayList();
            funcName = "user_Day";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            string.Empty,
                            null,
                            string.Empty,
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region Month
            ar = new ArrayList();
            funcName = "user_Month";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            string.Empty,
                            null,
                            string.Empty,
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region Year
            ar = new ArrayList();
            funcName = "user_Year";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            string.Empty,
                            null,
                            string.Empty,
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region Hour
            ar = new ArrayList();
            funcName = "user_Hour";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            string.Empty,
                            null,
                            string.Empty,
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region Minute
            ar = new ArrayList();
            funcName = "user_Minute";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            string.Empty,
                            null,
                            string.Empty,
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region LPAD_SPACE
            ar = new ArrayList();
            funcName = "user_LPAD_SPACE";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.StringType,
                            funcName,
                            "",
                            null,
                            "",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType),
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.NumericType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region CONVERT_DECIMAL
            ar = new ArrayList();
            funcName = "user_CONVERT_DECIMAL";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.StringType,
                            funcName,
                            "",
                            null,
                            "",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.NumericType),
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.NumericType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
         
        #region ISNULL
            ar = new ArrayList();
            funcName = "user_ISNULL";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.StringType,
                            funcName,
                            "",
                            null,
                            "",
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType),
                            new ICSSoft.STORMNET.FunctionalLanguage.FunctionParameterDef(langdef.StringType));
            funcDef.Language = langdef;
            ar.Add(funcDef);
            langdef.FunctionsByStringedViewList.Add(funcName, ar);
        #endregion
    }
     
    /// <summary>
    /// Определяем подстановки
    /// </summary>
    /// <param name="func"></param>
    /// <param name="convertValue"></param>
    /// <param name="convertIdentifier"></param>
    /// <returns></returns>
    string SQLTranslFunction(ICSSoft.STORMNET.FunctionalLanguage.Function func,
    ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.delegateConvertValueToQueryValueString convertValue,
    ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.delegatePutIdentifierToBrackets convertIdentifier)
    {
        string result = string.Empty;
         
        #region CHARINDEX
            if (func.FunctionDef.StringedView == "user_CHARINDEX")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format("CHARINDEX({0}, {1})", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format("INSTR({0}, {1}, 1, 1 )", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region LTRIM
            if (func.FunctionDef.StringedView == "user_LTRIM")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format("LTRIM({0})", func.Parameters[0].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format("LTRIM({0})", func.Parameters[0].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region LEFT
            if (func.FunctionDef.StringedView == "user_LEFT")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format("LEFT({0}, {1})", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format("SUBSTR({0}, 0, {1})", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region LEN
            if (func.FunctionDef.StringedView == "user_LEN")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format("LEN({0})", func.Parameters[0].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format("LENGTH({0})", func.Parameters[0].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region CAST AS NUMERIC
            if (func.FunctionDef.StringedView == "user_CAST_AS_NUMERIC")
            {
                // у меня на компе так: в SQL Server разделитель точка, в Oracle - запятая
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    // REPLACE(',', '.')
                    result = string.Format("CAST({0} AS NUMERIC)", func.Parameters[0].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    // REPLACE('.', ',')
                    result = string.Format("TO_NUMBER({0})", func.Parameters[0].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region Day
            if (func.FunctionDef.StringedView == "user_Day")
            {
                ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef();
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format(
                        "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' + CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
                        (DataService as SQLDataService).LimitFunction2SQLWhere(
                            langdef.GetFunction(langdef.funcDayPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format(
                        "LPAD(TO_CHAR({0}), 2, '0')",
                        (DataService as SQLDataService).LimitFunction2SQLWhere(
                            langdef.GetFunction(langdef.funcDayPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                 
                return result;
            }
        #endregion
         
        #region Month
            if (func.FunctionDef.StringedView == "user_Month")
            {
                ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef();
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format(
                                "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' + CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
                                (DataService as SQLDataService).LimitFunction2SQLWhere(
                                    langdef.GetFunction(langdef.funcMonthPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format(
                                "LPAD(TO_CHAR({0}), 2, '0')",
                                (DataService as SQLDataService).LimitFunction2SQLWhere(
                                    langdef.GetFunction(langdef.funcMonthPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                return result;
            }
        #endregion
         
        #region Year
            if (func.FunctionDef.StringedView == "user_Year")
            {
                ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef();
                result = (DataService as SQLDataService).LimitFunction2SQLWhere(
                                langdef.GetFunction(langdef.funcYearPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString()))));
                return result;
            }
        #endregion
         
        #region Hour
            if (func.FunctionDef.StringedView == "user_Hour")
            {
                ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef();
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format(
                        "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' + CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
                        (DataService as SQLDataService).LimitFunction2SQLWhere(
                            langdef.GetFunction(langdef.funcHHPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format(
                        "LPAD(TO_CHAR({0}), 2, '0')",
                        (DataService as SQLDataService).LimitFunction2SQLWhere(
                            langdef.GetFunction(langdef.funcHHPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                 
                return result;
            }
        #endregion
         
        #region Minute
            if (func.FunctionDef.StringedView == "user_Minute")
            {
                ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef();
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format(
                        "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' + CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
                        (DataService as SQLDataService).LimitFunction2SQLWhere(
                            langdef.GetFunction(langdef.funcMIPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format(
                        "LPAD(TO_CHAR({0}), 2, '0')",
                        (DataService as SQLDataService).LimitFunction2SQLWhere(
                            langdef.GetFunction(langdef.funcMIPart, new VariableDef(langdef.DateTimeType, RemoveFrameq(func.Parameters[0].ToString())))));
                }
                 
                return result;
            }
        #endregion
         
        #region LPAD_SPACE
            if (func.FunctionDef.StringedView == "user_LPAD_SPACE")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format("RIGHT (SPACE({1}) + CONVERT(VARCHAR({1}), {0}), {1} )", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format("LPAD({0}, {1})", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region CONVERT_DECIMAL
            if (func.FunctionDef.StringedView == "user_CONVERT_DECIMAL")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format(
                        "CASE WHEN {0} > REPLICATE('9', {1} - 2 - 1) + '.99' THEN LEFT ( SUBSTRING ( CONVERT(VARCHAR( 31 ), {0} ), 1, CHARINDEX ('.', CONVERT(VARCHAR( 31 ), {0} )) - 1), {1} ) ELSE LEFT ( CONVERT(VARCHAR( 31 ), {0} ), {1} ) END",
                        func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format(
                        "CASE WHEN {0} > RPAD('9', {1} - 2 - 1, '9') + ',99' THEN RPAD ( SUBSTR ( TO_CHAR({0}), 1, INSTR ('.', TO_CHAR({0}), 1, 1) - 1), {1} ) ELSE RPAD ( TO_CHAR({0}), {1} ) END",
                        func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                return result;
            }
        #endregion
         
        #region ISNULL
            if (func.FunctionDef.StringedView == "user_ISNULL")
            {
                if (this.DataService is ICSSoft.STORMNET.Business.MSSQLDataService)
                {
                    result = string.Format("ISNULL({0}, {1})", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format("NVL({0}, {1})", func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                return result;
            }
        #endregion
        return result;
    }
     
    /// <summary>
    /// Преобразует СУБД-независимую структуру Function в строку запроса для конкретной СУБД
    /// </summary>
    /// <param name="func"></param>
    /// <returns></returns>
    public string ParseLimitFunction(Function func)
    {
        return (this.DataService as SQLDataService).LimitFunction2SQLWhere(func);
    }
     
    /// <summary>
    /// Краткая запись для SQLDataService.PutIdentifierIntoBrackets
    /// </summary>
    /// <param name="ident"></param>
    /// <returns></returns>
    public string q(string ident)
    {
        string s = ident;
        if (ident.IndexOf("\".\"") == -1 && ident.IndexOf(".") != 0)
        {
            s = ident.Replace(".", "\".\"");
        }
         
        return (DataService as SQLDataService).PutIdentifierIntoBrackets(s);
    }
      
    public string RemoveFrameq(string ident)
    {
        return ident.TrimStart('"').TrimEnd('"');
    }
}

```
