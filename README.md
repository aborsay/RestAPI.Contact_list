# RestAPI.Contact_list
<h3>Summary</h3>
This is a simple project to show ability to create REST API in Java.<br/>
After some research I decided to do REST API using Spring Boot.<br/>
I have for the memory based database used H2.<br/>
Jackson is being used to create nested JSON responses.<br/>
Also included Spring Browser, this allowed for me to test the API with a little more ease.<br/>
This was created in IntelliJ IDEA, I am sure other IDE would work as well.<br/>
This is also Maven based.<br/>
I also used JSON viewer in Chrome in order to see the JSON with more clarity. 
<br/>
<br/>
LoadDatabase.java <br/>
This file is a current dummy file to create a small database for testing. <br/>The contacts are the 5 main hobbits from the LOTR and the Hobbit.

<h3>Install</h3>
Just open in selected IDE (as stated I used IntelliJ IDEA) and then run. <br/>
Going to http://localhost:8080/browser/index.html#/ will allow you to run NON-GET commands.<br/>
http://localhost:8080/contacts/ will show all of the current contacts. <br/>
Also, goto http://localhost:8080/contacts/{id}} for whatever id of an individual contacts <br/>
or http://localhost:8080/contacts/call-list to see list of all contacts with a home phone.

<h3>Notice:</h3>
Not sure is updating of contact or delete of contact is working. 
Used and tested in small contact object without the nested JSON as the input and it was working. However, had to adjust code for nested JSON and not sure.<br/>
In my browser aspect I lost the links and the abilities to add from NON-GET there.
<h3>To Do:</h3>
Test the updating of contacts and add more comments in code for explanation.<br/>
More error checking should be added. In particular it may not be watching for null pointers and what to put into the repository if the JSON does not include some parts besides the contacts name.<br/>
Add a standalone database rather then a memory based one, thus allowing changes to stay when restarting program.


<h3>Added:</h3>
Added StreetSecond to the Contact.class as I have an apartment and thought that would be the proper way for a contact. It is just addded but no data in it.<br/><br/>

<i>Was excited to use Java again. <br/>It has been some time. I may consider looking into Java certification to learn more beyond the Android coding I have done in the past. Was able to learn about Spring.</i><br/>
<i>Thanks to Spring.io, attacomsian.com, and likely other random pages for giving me pointers.</i>

<h2>June 6, 2021</h2>
Added more comments and is showing streetSecond
<h2>June 6, 2021</h2>
Commit to add blank streetSecond

<h2>June 6, 2021</h2>
First Commit everything has been tested except for the individual updating of the contacts.
