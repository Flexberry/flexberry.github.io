--- 
title: Associating images with data classes 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, image, class, property, object 
summary: a Way to link the image to the class object or data property 
toc: true 
permalink: en/fo_class-image.html 
lang: en 
autotranslated: true 
hash: c095f1220148da6efb72a97b8a8828afcf1c51754e25999dac3eb26fab0da29b 
--- 

[Data class](fo_data-object.html) can be associated to the image. 

* `ClassImageFile`. If you always want to use the same image for different instances of the data class, you should put this image into an Assembly as embedded (`embedded`) resource and specify the attribute `ClassImageFile`. 
* `ClassImageProperty`. If the image at different instances of the data class can be used `ClassImageProperty` attribute indicating a property of the data class that returns the image `System.Drawing.Image`. 
* `ExternalImageProvider`. Pictures can be provided by some external provider (attribute `ExternalImageProvider`). 

Except that the picture is displayed in the user interface (e.g., controls to work with a group of objects), the developer can obtain: 

* the picture for a particular data object by the method of [Information](fo_methods-class-information.html)`.GetImageForInstance`, 
* the picture class method `Information.GetClassImage`, 
* the name of the property that provides a picture method `Information.GetClassImageProperty`. 

{% include note.html content="In the build properties with the picture should be correct `Default Namespace` (corresponding to the namespace of the class to which the attached picture)." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}