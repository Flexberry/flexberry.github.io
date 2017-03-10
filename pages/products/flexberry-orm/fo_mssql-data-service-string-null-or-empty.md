---
title: MSSQLDataService String NULL or Empty
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_mssql-data-service-string-null-or-empty.html
folder: products/flexberry-orm/
lang: ru
---

# Строки в MSSQLDataService NULL или String.Empty
MSSQLDataService преобразует пустые строки в NULL при обновлении объектов. С другой стороны, когда строите LanguageDef-выражение, если параметром передаётся String.Empty, то запрос будет сгенерирован как IS NULL и всё сработает так как надо. Это упрощение, с одной стороны избавляет от необходимости писать сложные Where-выражения, с другой стороны кому-то может потребоваться отдельные NULL и String.Empty, в этом случае, можно унаследоваться от MSSQLDataService и переопределить метод 
            '''```
public virtual string ConvertSimpleValueToQueryValueString(object value)
```''' таким образом, чтобы не выполнялась замена 
            '''```
if (valType == typeof(string))
            {
                if ((string)value == string.Empty)
                    return "NULL";
                else
                    return "'" + value.ToString().Replace("'", "''") + "'";
            }```'''

----
Особое внимание следует обратить на эту особенность, если используются импортированные данные или SQL-выражения, которые выполняются в обход DataService.



