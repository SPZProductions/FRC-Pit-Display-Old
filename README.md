# SPZ Production's FRC Team Pit Display
A Simple to use, easy to understand FRC Pit Display, using the Blue Alliance API
#### The Pit Display Program can be [Downloaded Here](https://github.com/SPZProductions/FRC-Pit-Display/releases)

##How to Use (Basic Step-By-Step):
1. Ensure that the computer running the pit display is connect to the internet 
2. Download the latest Release from [the Releases Page](https://github.com/SPZProductions/FRC-Pit-Display/releases)
3. Extract the Downloaded Zip to desired Folder
4. In a Web Browser, Navigate to https://www.thebluealliance.com/events(https://www.thebluealliance.com/events)
5. On the Left-Hand Side, Select the Week that your Competition is in(If you don't know, continue to next step)
	Ex. Week 1
6. Scroll down untill you see your event name
	Ex. FIM District - Kettering University Event #1 
7. Cick on the title
8. In the URL bar, Note the Event Code (Usally the year followed by 5 or 6 characters representing the state and event location)
	Ex. 2017miket
9. Exit out of the Web Browser and Navigate to the Folder where you Extracted the Zip File you Downloaded Earlier
10. Run SPZ_Productions_FRC_Pit_Display.jar
11. At the Top of the new window that apears, look for the Team number and Event Key fields
12. Enter in the Team Number of the Team you Wish to Have Shown on the Pit Display
	Ex. 1322
13. Enter the 5 ot 6 characters you noted earlier
	Ex. miket
14. Click the "Update" Button at the Bottom Right-Hand corner
15. You Should see your Team's Number, Name, Sponsors, And Motto Change on the Main Window
16. Play Around with and Customize the Settings, Untill you Find Something that you Think Looks Good
17. Click the "Update" Button
18. Close the Settings Window
19. Click F5 on your Keyboard to Enter Fullscreen Mode(F5 again or ESC to exit)
20. Hit F1 on your Keyboard to Re-Open the Settings Window after you closed it
21. ENJOY!

###[Request A Custom Screen Size](https://github.com/SPZProductions/FRC-Pit-Display/issues/7)


## Development Enviroment Setup Instructions:
### NetBeans 8.1 Java SE
1. Install the latest version of [NetBeans Java SE](https://netbeans.org/downloads/)
2. Install the latest version of [Java SE Development Kit](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
3. Clone this repository to your development enviroment
4. Open Project in NetBeans (File > Open Project)
5. Right Click Libraries > Add JAR/Folder...
6. Browse to /lib in the cloned repository on your development enviroment and add both TBA-APIv3 and json-simple-1.1.1 as JAR/Folders

This project was developed primarily in NetBeans IDE 8.1 with Java SDK 8 Update 73 (build 1.8.0_73-b02).

