--- 
title: configure the names of the entity types in OData metadata 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry Ember, odata, metadata 
summary: 
toc: true 
permalink: en/fo_metadata-for-client.html 
lang: en 
autotranslated: true 
hash: c1c0485e3b6bd1a3b9d16f8a7364868e428d7c86d03cf8c5d12bd7bf0215cd72 
--- 

For converting type names meets two delegates `EntityTypeNamespaceBuilder` and `EntityTypeNameBuilder`. 

```javascript
/// <summary> 
/// Delegate for building names for EDM entity type. 
/// </summary> 
public Func<Type, string> EntityTypeNameBuilder { get; set; }
``` 

In `EntityTypeNameBuilder` you can implement the fixed functionality, i.e. to make the analog method: 

```javascript
/// <summary> 
/// Builds the name of the entity. 
/// Default logic, for <see cref="EntityTypeNameBuilder"/>. 
/// </summary> 
/// <param name="dataObjectType">Type of the data object.</param> 
/// <returns>The name of The appropriate EDM entity.</returns> 
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

[Source method](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ODataService/blob/9831629a8ffe52234d199bd8692090e240b8828c/NewPlatform.Flexberry.ORM.ODataService/Model/DefaultDataObjectEdmModelBuilder.cs#L267). 

PstrfEntityTypeNamespaceBuilder` for a method by default returns empty string, so it will also need to override: 

```javascript
builder.EntityTypeNamespaceBuilder = (dataObjectType) => dataObjectType.Namespace;
``` 
The name of the entity type in OData must be complete, i.e. with namespace. Accordingly, if no namespace, the full name of the entity type will look like ".ClassName": 

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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}