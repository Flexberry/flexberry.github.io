---
title: Скобки программиста
sidebar: product--sidebar
keywords: CASE Plugins, Flexberry ORM, Public
toc: true
permalink: ru/programmer-brackets.html
folder: product--folder
lang: ru
---

namespace IIS.Product_20008
{
    using System;
    using System.Xml;
    using ICSSoft.STORMNET;
    
    /// <summary>
    /// Клиент
    /// </summary>
    // *** Start programmer edit section *** (Клиент CustomAttributes)

    // *** End programmer edit section *** (Клиент CustomAttributes)
    [AutoAltered()]
    [AccessType(ICSSoft.STORMNET.AccessType.none)]
    [View("КлиентE", new string[] {
            "ФИО as \'ФИО\'",
            "Прописка as \'Прописка\'"})]
    [View("КлиентL", new string[] {
            "ФИО as \'ФИО\'",
            "Прописка as \'Прописка\'"})]
    public class Клиент : ICSSoft.STORMNET.DataObject
    {
        
        private string fФИО;
        
        private string fПрописка;
        
        // *** Start programmer edit section *** (Клиент CustomMembers)

        // *** End programmer edit section *** (Клиент CustomMembers)

        
        /// <summary>
        /// Клиент
        /// </summary>
        // *** Start programmer edit section *** (Клиент.ФИО CustomAttributes)

        // *** End programmer edit section *** (Клиент.ФИО CustomAttributes)
        [StrLen(255)]
        public virtual string ФИО
        {
            get
            {
                // *** Start programmer edit section *** (Клиент.ФИО Get start)

                // *** End programmer edit section *** (Клиент.ФИО Get start)
                string result = this.fФИО;
                // *** Start programmer edit section *** (Клиент.ФИО Get end)

                // *** End programmer edit section *** (Клиент.ФИО Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Клиент.ФИО Set start)

                // *** End programmer edit section *** (Клиент.ФИО Set start)
                this.fФИО = value;
                // *** Start programmer edit section *** (Клиент.ФИО Set end)

                // *** End programmer edit section *** (Клиент.ФИО Set end)
            }
        }
        
        /// <summary>
        /// Клиент
        /// </summary>
        // *** Start programmer edit section *** (Клиент.Прописка CustomAttributes)

        // *** End programmer edit section *** (Клиент.Прописка CustomAttributes)
        [StrLen(255)]
        public virtual string Прописка
        {
            get
            {
                // *** Start programmer edit section *** (Клиент.Прописка Get start)

                // *** End programmer edit section *** (Клиент.Прописка Get start)
                string result = this.fПрописка;
                // *** Start programmer edit section *** (Клиент.Прописка Get end)

                // *** End programmer edit section *** (Клиент.Прописка Get end)
                return result;
            }
            set
            {
                // *** Start programmer edit section *** (Клиент.Прописка Set start)

                // *** End programmer edit section *** (Клиент.Прописка Set start)
                this.fПрописка = value;
                // *** Start programmer edit section *** (Клиент.Прописка Set end)

                // *** End programmer edit section *** (Клиент.Прописка Set end)
            }
        }
        
        /// <summary>
        /// Class views container
        /// </summary>
        public class Views
        {
            
            /// <summary>
            /// "КлиентE" view
            /// </summary>
            public static ICSSoft.STORMNET.View КлиентE
            {
                get
                {
                    return ICSSoft.STORMNET.Information.GetView("КлиентE", typeof(IIS.Product_20008.Клиент));
                }
            }
            
            /// <summary>
            /// "КлиентL" view
            /// </summary>
            public static ICSSoft.STORMNET.View КлиентL
            {
                get
                {
                    return ICSSoft.STORMNET.Information.GetView("КлиентL", typeof(IIS.Product_20008.Клиент));
                }
            }
        }
    }
}
```

В этом классе встречается 12 скобок программиста, позволяющих внести изменения в любую часть кода. У каждой скобки свое предназначение и свое место. К примеру, скобки ```cs // *** Start programmer edit section *** (Клиент CustomMembers)

 // *** End programmer edit section *** (Клиент CustomMembers)``` отвечают за добавление собственных членов класса. К примеру, если мы захотим добавить метод, возвращающий строку вида "ФИО (Прописка)", то добавлять этот метод нужно будет именно в скобки. 

Предположим, что метод выглядит следующим образом:

```cs 
public string GetFullClientString()
{
    return string.Format("{0} ({1})", ФИО, Прописка);
}
```


Тогда, после добавления его в __правильное__ место, код класса (отрывок) будет выглядеть так:
```cs
        private string fФИО;
        
        private string fПрописка;
        
        // *** Start programmer edit section *** (Клиент CustomMembers)

        public string GetFullClientString()
        {
            return string.Format("{0} ({1})", ФИО, Прописка);
        }

        // *** End programmer edit section *** (Клиент CustomMembers)
```

# Возникающие ошибки
Если при генерации через Flexberry в лог выводится информация следующего тип:
`Ошибка: Не найдено завершение скобки в файле "D:\Проекты\КредитыTestBuild\Кредиты\Objects\Клиент.cs"`, значит где-то в указанном файле была удалена или добавлена лишняя скобка.

Если при генерации приложения возникает исключение типа <!--http://storm:20013/class_s_t_o_r_m_c_a_s_e_1_1_s_t_o_r_m_n_e_t_1_1_generator_1_1_duplicate_brace_founded_exception.html-->DuplicateBraceFoundedException, значит где-то добавлена лишняя открывающая скобка.

Если при генерации приложения возникает исключение типа <!--http://storm:20013/class_s_t_o_r_m_c_a_s_e_1_1_s_t_o_r_m_n_e_t_1_1_generator_1_1_not_found_end_of_brace_exception.html-->NotFoundEndOfBraceException, значит где-то удалена закрывающая скобка.

