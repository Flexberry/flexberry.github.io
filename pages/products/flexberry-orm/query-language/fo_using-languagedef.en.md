--- 
title: Creating your own functions using the LanguageDef 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Development of language restrictions 
toc: true 
permalink: en/fo_using-languagedef.html 
lang: en 
autotranslated: true 
hash: d7c7a6462dcf6e2953117a2135d18d67cd8dfa2b4ff2c6f45e93602fc0e742d4 
--- 

There are the standard "Builder" features, the restrictions, [SQLWhereLanguageDef](fo_function-list.html) and [ExternalLangDef](fo_external-lang-def.html). 
Your function when you use a LanguageDef (language constraint) allows you to create a DBMS-independent design and use limitations [FunctionalLanguage](fo_limit-function.html) or hand-written scripts. Using this approach allows you to create more light accompanied by a code, rather than writing redundant expressions for each DBMS. 

{% include important.html content="User-defined functions must start with the characters `user_`!. "%} 

### Example 

The development of language restrictions ExportLanguage, which will be made in the following manner: 

```csharp
// Create an instance of the language. 
ExportLanguage langForExport = new ExportLanguage(this.DataService);
ICSSoft.STORMNET.Windows.Forms.ExternalLangDef myLangDef = langForExport.Language;
myLangDef.GetFunction("user_LPAD_SPACE", langForExport.q("So Nomercy"), 8)
myLangDef.GetFunction("user_Day", langForExport.q("T. Breathability"))
``` 

Class `ExternalLangDef` not allow one instance to contain predefined functions and raised hands, so you have to use 2 instance of the language (instances are no different, except for the functions that are used to write expressions): 

* new ExternalLangDef() 
* (new ExportLanguage()).Language. 

`Функция q` is ancillary, and is not part of the approach. It is easy to generate code for a large amount, shared the ID containing point IDs, going through the point. 

```csharp
using ICSSoft.STORMNET.UI;
using ICSSoft.STORMNET.Business;
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;
using System.Collections;
  
public class ExportLanguage
{
    /// <summary> 
    /// DataService for a specific DBMS. 
    /// </summary> 
    private IDataService DataService;
     
    /// <summary> 
    /// Constructor, which requires the definition of a service to a particular DBMS. 
    /// </summary> 
    /// <param name="dataService"></param> 
    public ExportLanguage(IDataService dataService)
    {
        DataService = dataService;
    }
     
    /// <summary> 
    /// Creates an instance of the language containing the given functions (and only them) 
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
    /// Set language (in this case the standard features are discarded) 
    /// </summary> 
    /// <param name="langdef">an instance of the language to set</param> 
    public void TuneUp_Language(ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef)
    {
        langdef.UserSQLTranslFunction = new ICSSoft.STORMNET.Windows.Forms.ExternalLangDef.delegateUserSQLTranslFunction(SQLTranslFunction);
        CreateNewLimitFunctions(langdef);
    }
     
    /// <summary> 
    /// Define functions (standard function will be impossible) 
    /// </summary> 
    /// <param name="langdef"></param> 
    public void CreateNewLimitFunctions(ICSSoft.STORMNET.Windows.Forms.ExternalLangDef langdef)
    {
        string funcName = string.Empty;
        FunctionDef funcDef = null;
         
        // to get FuncID 
        // FuncID - the function ID in the language. 
        // Standard functions have numbers starting with one and increasing in the order of vozrastaniya number. 
        // So not to use the occupied rooms will count down from the maximum value. 
        int i = int.MaxValue - 1;
        ArrayList ar;
         
        #region CHARINDEX
            ar = new ArrayList();
            funcName = "user_CHARINDEX";
            funcDef = new ICSSoft.STORMNET.FunctionalLanguage.FunctionDef(
                            i--,
                            langdef.NumericType,
                            funcName,
                            "The position of character in string",
                            null,
                            "(The position of the symbol ({0}) at line {1})",
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
                            "Trim spaces on the left",
                            null,
                            "(The trimming of spaces left({0}))",
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
                            "Cutting the length of a string",
                            null,
                            "(Cutting the length of the string({0}))",
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
                            "String length",
                            null,
                            "(The length of the string({0}))",
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
    /// Define lookup 
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
                // on my computer: SQL Server uses the point as delimiter in Oracle. 
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
                        "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
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
                                "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
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
                        "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
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
                        "CASE WHEN {0} < 10 AND {0} >= 0 THEN '0' CONVERT(VARCHAR, {0}) ELSE CONVERT(VARCHAR, {0}) END",
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
                    result = string.Format("RIGHT (SPACE({1}) CONVERT(VARCHAR({1}), {0}), {1} )", func.Parameters[0].ToString(), func.Parameters[1].ToString());
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
                        "CASE WHEN {0} > REPLICATE('9', {1} - 2 - 1) '.99' THEN LEFT ( SUBSTRING ( CONVERT(VARCHAR( 31 ), {0} ), 1, CHARINDEX ('.', CONVERT(VARCHAR( 31 ), {0} )) - 1), {1} ) ELSE LEFT ( CONVERT(VARCHAR( 31 ), {0} ), {1} ) END",
                        func.Parameters[0].ToString(), func.Parameters[1].ToString());
                }
                 
                if (this.DataService is ICSSoft.STORMNET.Business.OracleDataService)
                {
                    result = string.Format(
                        "CASE WHEN {0} > RPAD('9', {1} - 2 - 1, '9') ',99' THEN RPAD ( SUBSTR ( TO_CHAR({0}), 1, INSTR ('.', TO_CHAR({0}), 1, 1) - 1), {1} ) ELSE RPAD ( TO_CHAR({0}), {1} ) END",
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
    /// Converts the DBMS-independent structure Function in the query string for a specific DBMS 
    /// </summary> 
    /// <param name="func"></param> 
    /// <returns></returns> 
    public string ParseLimitFunction(Function func)
    {
        return (this.DataService as SQLDataService).LimitFunction2SQLWhere(func);
    }
     
    /// <summary> 
    /// Short entry for SQLDataService.PutIdentifierIntoBrackets 
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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}