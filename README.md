# SPZ Production's FRC Team Pit Display
A Simple to use, easy to understand FRC Pit Display, using the Blue Alliance API
#### The Pit Display Program can be [Downloaded Here](https://github.com/SPZProductions/FRC-Pit-Display/releases)

##How to Use (Basic Step-By-Step):
1. Ensure that the computer running the pit display is connect to the internet 
2. Download the latest Release from [the Releases Page](https://github.com/SPZProductions/FRC-Pit-Display/releases)
3. Extract the Downloaded Zip to desired Folder
4. In a Web Browser, Navigate to https://www.thebluealliance.com/events[https://www.thebluealliance.com/events]
5. On the Left-Hand Side, Select the Week that your Competition is in(If you don't know, continue to next step)
	Ex. Week 1
6. Scroll down untill you see your event name
	Ex. FIM District - Kettering University Event #1 
7. Cick on the title
8. In the URL bar, Note the Event Code (Usally the year followed by 5 or 6 characters representing the state and event location)
	Ex. 2016miket
9. Exit out of the Web Browser and Navigate to the Folder where you Extracted the Zip File you Downloaded Earlier
10. Run SPZ_Productions_FRC_Pit_Display.jar
11. At the Top of the new window that apears, look for the Year, Team number, and Event Key fields
12. Enter the Year of the Competiton you Wish to Load(Usally the current year)
	Ex. 2016
13. Enter in the Team Number of the Team you Wish to Have Show on the Pit Display
	Ex. 1322
14. Enter the 5 ot 6 characters you noted earlier
	Ex. miket
15. Click the "Update" Button at the Bottom Right-Hand corner
16. You Should see your Team's Number, Name, Sponsors, And Motto Change on the Main Window
17. Play Around with and Customize the Settings, Untill you Find Something that you Think Looks Good
18. Click the "Update" Button
19. Close the Settings Window
20. Click F5 on your Keyboard to Enter Fullscreen Mode(F5 again or ESC to exit)
21. Hit F1 on your Keyboard to Re-Open the Settings Window after you closed it
21. ENJOY!

##How to Setup Sponsors
1. Get Ahold of PNG Versions of your Sponsor's Logos
2. Navigate to the Folder where you Extracted the Zip File you Downloaded Earlier
3. Open the "sponsors.png" File in [pixlr](https://pixlr.com/editor/) (or a picture editor of your choosing, I chose pixlr due to its ability to use clear backgrounds)
4. Add/Import your Sponsor's Logos, But Do NOT change the size of the file, should be 707px Wide by 401px High
5. Save the File as a PNG named "sponsors.png" in the Same Directory that you extracted the Zip File in Earlier
6. Run SPZ_Productions_FRC_Pit_Display.jar
7. In the Bottom of the Settings Window, Click on "Refresh Sponsors Image"
8. If the Sponsors Image does not Apear, Ensure that the File is Named "sponsors.png" and is in the Same Directory as the SPZ_Productions_FRC_Pit_Display.jar file.
9. Customize the Rest of the Settings the way that you Like
9. ENJOY!



## Development Enviroment Setup Instructions:
### NetBeans 8.1 Java SE
1. Install the latest version of [NetBeans Java SE](https://netbeans.org/downloads/)
2. Install the latest version of [Java SE Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
3. Clone this repository to your development enviroment
4. Open Project in NetBeans (File > Open Project)
5. Right Click Libraries > Add JAR/Folder...
6. Browse to /lib in the cloned repository on your development enviroment and add both TBA-APIv3 and json-simple-1.1.1 as JAR/Folders

This project was developed primarily in NetBeans IDE 8.1 with Java SDK 8 Update 73 (build 1.8.0_73-b02).

