--- 
title: Events WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-events.html 
lang: en 
autotranslated: true 
hash: 055e486e5f1d3633f793f72a507739eb8f8f322fe873ef6ee08acbe0c37f95b4 
--- 

All the event arguments [WebObjectListView](fa_web-object-list-view.html) inherited from base: 

```csharp
/// <summary> 
/// Argument type for events WOLV. 
/// </summary> 
public class WolvEventArgs : CancelEventArgs
{
}
``` 

## the List of events 

| Event | Description | 
| ------- | -------- | 
| `ObjectAdding` | Transition to object creation. | 
| `ObjectEditing` | Transition to the edit form of the object. | 
| `ObjectPrototyping` | Transition to the prototyping object. | 
| `ObjectDeleting` | object Deletion started. The event will be called for each deleted object. | 
| `ObjectsDeleted` | Objects departed. You cannot generate event delete for each object. | 

* Have the ability to customize their message to users after deleting the object. For the formation of the message, you can use the list of deleted objects and 
the list of objects that failed to delete. 
* Also has the ability to convey the status of the removal of honey early removal and end removal. 

## the event Arguments 

Events `ObjectEditing` and `ObjectPrototyping` in the arguments contains the primary key of the data object - `DataObjectPrimaryKey`. 

Through the event arguments `ObjectDeleting` you can get to the deleted object data `DataObj`, and change it. 

The event arguments `ObjectsDeleted` allow you to edit or clear a message to the user - `UserMessage`. 

To store application metadata between the event and the event `ObjectDeleting` `ObjectsDeleted` used `DeletingState` and `DeletedState` available through the arguments of these events. 

## Cancel events 

Any event can be cancelled by selecting the arguments `Cancel = true`, because all the arguments are inherited from `CancelEventArgs`. 

If an exception occurred when deleting an object, you can handle signing up for the event `ExceptionThrown`. 

## Own event handler `ObjectDeleting` 

If you implement your own handler `ObjectDeleting` and set the arguments `Cancel = true`, then you need in the event handler `ObjectsDeleted` to clean or change its message to the user, which is stored `args.UserMessage`. Otherwise, the message "deleted object: 1, because some objects have been locked.", even if the objects were removed successfully. 

## Example manual handling events `ObjectDeleting` for processing the [cascade deleting objects](fo_cascade-delete.html) 

The concept of event processing: 

1. Subscribe to the event `ObjectDeleting`. 
2. To write a handler to handle the cascade delete related objects to be deleted. 
3. Write code for the business server, which will inform the handler about the objects associated with the deleted. 
4. If any, then prompt the user for permission to delete related objects (showing him what the objects are removed). 
5. If the user will be allowed to cascade delete, then execute it. 

### the First stage. Event subscription 

__The first option.__ To sign up statically (in the markup page that contains [WebObjectListView](fa_web-object-list-view.html). She called `wolv_page.aspx`). 

```xml
<%-- Разметка из wolv_page.aspx --%>;
<ac:WebObjectListView ID="WebObjectListView1" runat="server" OnObjectDeleting="WebObjectListView1_ObjectDeleting" />;
``` 

__The second option.__ To subscribe from code (in the code-behind page that contains WOLV. Let the code file is called `wolv_page.aspx.cs`). 

```csharp
// Code from wolv_page.aspx.cs 
protected override void Preload()
{
    WebObjectListView1.ObjectDeleting += WebObjectListView1_ObjectDeleting;
}
``` 

{% include note.html content="When you subscribe to an event from code Studio will offer automatic generation of event handler. If you press `Tab` after 
printed =, it will generate the name of the handler `WebObjectListView1_ObjectDeleting.` 

If you again press `Tab` will be generated pattern handler: 

```csharp
protected void WebObjectListView1_ObjectDeleting(WebObjectListView sender, WolvEventArgs args)
``` 
"%} 

### the Second stage. Processing events 

```csharp
// Code from wolv_page.aspx.cs 

// Collection objects in the format [{objectPK: the String representation of the primary key of the deleted addresses, confirmMessage: message Text of reference to the address object}, ...] 
private List<object> _deletingObjectsCollection = new List<object>();

// This handler will be consistently invoked for each object selected for deletion. 
protected void WebObjectListView1_ObjectDeleting(WebObjectListView sender, WolvObjectDeletingEventArgs args)
{   
    // Mark the received object to delete (in this example it is the address of the facility). 
    args.DataObj.SetStatus(ICSSoft.STORMNET.ObjectStatus.Deleted);
    try
    {   // Try to remove (further processing occurs in the Business server, Adress.cs). 
        // If during uninstall an error occurs, we will be able to handle it in the catch block. 
        // If Adress.cs will find companies that reference the deleted address, it will throw ConfirmAdresDeletingException. 
        ICSSoft.STORMNET.Business.DataServiceProvider.DataService.UpdateObject(args.DataObj);
    }
    // Handle the exception business server 
    catch (ConfirmAdresDeletingException ex)
    {   
        // If we got this piece of code was a means for the detected objects referencing the deleted address. 
        // Confirmation is required cascade delete. 
        // Store information about references to deleted address. After a page reload, it will take the client code. 
        // objectPK is the primary key of the referencing object, confirmMessage the message object generated in the business server. 
        _deletingObjectsCollection.Add(new { objectPK = args.DataObj.__PrimaryKey.ToString(), confirmMessage = ex.Message });
     }
     //Cancel the further execution of the removal of WOLV-Ohm, because we have fulfilled it with your hands. 
     //If this is not done, then the code on the business server will run again. 
     args.Cancel = true;
}

// Returns the serialized JSON string of the format "[{"objectPK": "the String representation of the primary key of the deleted addresses", "confirmMessage": "the text of the reference, to the address of the object"}, ...]". 
public string DeletingObjects
{
    get
    {
        //The JavaScriptSerializer is in the System namespace.Web.Script.Serialization; 
        JavaScriptSerializer serializer = new JavaScriptSerializer();
        //Escapes quotes 
        return serializer.Serialize(_deletingObjectsCollection).Replace("\"", "\\\"");
    }
}
``` 

### the Third stage. Getting in the business server information on the connected objects 

```csharp
//Code from Adress.cs 
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateАдрес(IIS.MedicalInstitutionsGuide.Адрес UpdatedObject)
{
    // *** Start programmer edit section *** (OnUpdateАдрес) 
    //In case the processing is interrupted, return an empty array DataObject[] 
    DataObject[] result = new DataObject[0];

    if (UpdatedObject.GetStatus() == ObjectStatus.Deleted)
    {
        //Get the list of institutions referring to the deleted address 
        var ds = (SQLDataService)DataServiceProvider.DataService;
        МедицинскоеУчреждение[] mil = ds.Query<МедицинскоеУчреждение>(МедицинскоеУчреждение.Views.MIG_МедицинскоеУчреждениеE)
            .Where(o => o.Адрес.__PrimaryKey == UpdatedObject.__PrimaryKey)
            .ToArray();
        //If list is not empty, then it will need the cascading delete 
        //Need to check whether the user confirmation, and if not, request it 
        if (mil.Length > 0)
        {
            //If user confirmed a cascading delete, in DynamicProperties of the property is a flag that the key "DeletingAllowed" 
            if (UpdatedObject.DynamicProperties.ContainsKey("DeletingAllowed"))
            {
                //Mark Agency for the removal 
                foreach (МедицинскоеУчреждение mi in mil)
                {
                    mi.SetStatus(ObjectStatus.Deleted);
                }
                //Return the deleted companies, so they were removed in the same transaction 
                result = mil;
            }
            else
            {
                //Need to prompt the user for confirmation on delete cascade institutions, followed by their address 
                //Generated string with the names of the institutions 
                string referencedObjects = String.Join(", ", mil.Select(o => String.Format("'{0}'", o.Название)));
                //Early removal, by sending in the drop handler, the message about referencing objects 
                //Method UpdatedObject.ToString(true) just returns the address string, for example "Russia, Perm region, Perm, Brothers Ignatovich, 2" 
                throw new MIG_ConfirmAdresDeletingException(String.Format("Вы уверены, что хотите удалить адрес: '{0}'? На него ссылаются следующие медицинские учреждения: {1}. 
                    Они так же будут удалены вместе с информацией об их телефонах и расходах.",
                    UpdatedObject.ToString(true),
                    referencedObjects));
            }
        }
    }
    return result;
    // *** End programmer edit section *** (OnUpdateАдрес) 
}
``` 

### the Fourth stage. Confirmation on cascade delete of objects 

The confirmation message with [JavaScript API](fa_js-api-core.html): 

```javascript
//Code from wolv_page.aspx 
//wiki forbids SCRIPT tag-and therefore facing underline, in real code it should not be. 
<asp:Content ID="scriptContent" ContentPlaceHolderID="ContentPlaceHolder0" runat="server">
    <_script type="text/javascript">
        //Client processing cascade delete addresses, and of referencing them, the objects 
        $(document).ready(function () {
            //Collect from the server (via a public property DeletingObjects)serialized JSON string format of the form 
            //"[{"objectPK": "the String representation of the primary key of the deleted addresses", 
            // "confirmMessage": "the text of the reference, to the address of the object"}, ...]" and deserialize it 
            var deletingObjects = $.parseJSON("<%=DeletingObjects%>");
            //Perform processing only if at least one address is required cascade delete 
            if (deletingObjects.length > 0) {
                //String to tell the user 
                var confirmMessage = "";
                //String JSON format for the primary keys of the deleted addresses (for sending to the server, in the case of a delete confirmation) 
                var deletingObjectsPK = "";
                var lastIndex = deletingObjects.length - 1;
                //Generated string 
                for (var i = 0; i < deletingObjects.length; i++) {
                    confirmMessage = confirmMessage + (i + 1) + "). " + deletingObjects[i].confirmMessage + "\n";
                    deletingObjectsPK = deletingObjectsPK + "\"" + deletingObjects[i].objectPK + "\"";
                    if (i < lastIndex) {
                        deletingObjectsPK = deletingObjectsPK + ", ";
                    }
                }
                deletingObjectsPK = "[" + deletingObjectsPK + "]";
                //Display a confirmation window 
                $.ics.dialog.confirm({
                    message: confirmMessage,
                    title: 'Подтверждение каскадного удаления связанных объектов',
                    okButtonText: "Anyway, to remove",
                    cancelButtonText: "Cancel",
                    callback: function (res) {
                        if (res) {
                            //When OK is pressed. 
                            //Initiate the PostBack, passing, as argument, 
                            //the JSON string format with primary keys odulami addresses. 
                            //'confirmDeletingOkBtn' - the name of the button on the confirmation dialog, which initiates the PostBack. 
                            __doPostBack('confirmDeletingOkBtn', deletingObjectsPK);
                        } else {
                            //When pressing Cancel. 
                            alert("Cascade deletion canceled.");
                        }
                    }
                });
            }
        });
    </_script>
</asp:Content>
``` 

The user will receive the following confirmation window: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/confirm-cascade-deleting.png) 

### the Fifth stage. To perform a cascade delete objects if the user has consented 

```csharp
// Code from wolv_page.aspx.cs 
protected override void Preload()
{
    // *** Start programmer edit section *** (PostBack handling) 
    if (Page.IsPostBack)
    {
        //Handle the case where the PostBack occurred when confirmation by the user 
        //cascade delete selected addresses and referencing them, the objects. 

        //Get the name of the object that caused the PostBack 
        string targetID = Request.Form["__EVENTTARGET"];
        if (!String.IsNullOrEmpty(targetID))
        {
            //If the object is a "OK" button of confirmation window 
            if (targetID.Equals("confirmDeletingOkBtn"))
            {
                //Get the JSON string format with the primary keys of the deleted addresses 
                string argument = Request.Form["__EVENTARGUMENT"];
                //Perform deserialization of string 
                JavaScriptSerializer serializer = new JavaScriptSerializer();
                List<string> deletingObjectsPK = serializer.Deserialize<List<string>>(argument);
                //Forming an array of deleted addresses 
                DataObject[] deletingAdreses = deletingObjectsPK.Select(pk =>
                {
                    //Create an object of class "Address" with the desired primary key 
                    Адрес deletingAdres = new Адрес();
                    deletingAdres.SetExistObjectPrimaryKey(new Guid(pk));
                    //Confirm the cascade delete of the objects that link to this address 
                    //by the presence, in DynamicProperties the key "DeletingAllowed", the business server will understand that cascading deletes are allowed 
                    deletingAdres.DynamicProperties.Add("DeletingAllowed", true);
                    //Mark the address for deletion 
                    deletingAdres.SetStatus(ObjectStatus.Deleted);
                    //Add to the array 
                    return deletingAdres;
                }).ToArray<Адрес>();
                //Delete. (Further processing takes place in Adress) 
                DataServiceProvider.DataService.UpdateObjects(ref deletingAdreses);
            }
        }
    }
    // *** End programmer edit section *** (PostBack handling) 
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}