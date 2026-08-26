---
title: Настройка имен типов сущностей в метаданных OData
sidebar: flexberry-orm_sidebar
keywords: Flexberry Ember, odata, метаданные
summary: 
toc: true
permalink: ru/fo_metadata-for-client.html
lang: ru
---

За преобразование имен типов отвечает два делегата `EntityTypeNamespaceBuilder` и `EntityTypeNameBuilder`.

```javascript
/// <summary>
/// Delegate for building names for EDM entity type.
/// </summary>
public Func<Type, string> EntityTypeNameBuilder { get; set; }
```

В `EntityTypeNameBuilder` можно реализовать исправленную функциональность, т.е. сделать свой аналог метода:

```javascript
/// <summary>
/// Builds the name of the entity.
/// Default logic, for <see cref="EntityTypeNameBuilder"/>.
/// </summary>
/// <param name="dataObjectType">Type of the data object.</param>
/// <returns>The name of appropriate EDM entity.</returns>
private string BuildEntityTypeName(Type dataObjectType)
{
    PublishNameAttribute attr = dataObjectType.GetCustomAttribute<PublishNameAttribute>(false);
    if (attr != null)
    {
        int lastPos = attr.TypePublishName.LastIndexOf(".");
        if (lastPos < 0)
            return attr.TypePublishName;
        return attr.TypePublishName.Substring(lastPos + 1);
    }
 
    return dataObjectType.Name;
}
```

[Исходник метода](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/9831629a8ffe52234d199bd8692090e240b8828c/NewPlatform.Flexberry.ORM.ODataService/Model/DefaultDataObjectEdmModelBuilder.cs#L267).

Для метода `EntityTypeNamespaceBuilder` по умолчанию возвращается пустая строка, поэтому его также необходимо переопределить:

```javascript
builder.EntityTypeNamespaceBuilder = (dataObjectType) => dataObjectType.Namespace;
```
Имя типа сущности в OData должно быть полным, т.е. вместе с namespace. Соответственно, если namespace нет, то полное имя типа сущности будет выглядеть ".ClassName":

```javascript
<EntityType Name="DocumentFileKind" BaseType=".Catalog" OpenType="true">
    <Property Name="CreateTime" Type="Edm.DateTimeOffset" />
    <Property Name="Creator" Type="Edm.String" />
    <Property Name="EditTime" Type="Edm.DateTimeOffset" />
    <Property Name="Editor" Type="Edm.String" />
    <NavigationProperty Name="NewName" Type=".DocumentFileKind" />
</EntityType>
 
<EntitySet Name="FederalLaws" EntityType=".FederalLaw">
    <NavigationPropertyBinding Path="LawIntroductionReason" Target="LawIntroductionReasons" />
</EntitySet>
```
