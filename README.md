Group Project
Karan Vadhan
Josh Improgo
Jonas Improgo
===

# UniversityCrew

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This is a college-based app where college students are able to eliminate the social unease of talking to students within their classes. This app allows students to be linked to their own class and the students who have the app will be able to select their class and share notes, message for any upcoming tests or reminders or important announcements that their professors have made. This app also allows for club meetings and events as reminders in their own calendar.

### App Evaluation
- **Category:**
    - This app is directly focused on 
- **Mobile:**
    - The app may be used as another messaging system as well as a reminder on the phone
- **Story:**
    - Users are able to gain value from being able to communicate with other students and having reminders
- **Market:**
    - This app combines a communication app with the students in their classes and the schedule organization of a calendar.
- **Habit:**
    - Students will make a habit of asking for notes daily or communicating with other students for assistance or even making study groups. The calendar fragment also allows students to be reminded of upcoming events or club meetings.
- **Scope:**
    - The scope of this app is reliant on college students within their own universities.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User is able to find a list of the available courses in their college
* User is able to search the course by name, semester, year, Professor name
* User is able to add a course if it one is not available
* User is able to communicate with other students within the class
* User is able to share notes and attachments with other students
* User is able to edit their Profile picture
* User is able to add events and clubs to their Calendar
* User is able to receive notifications of important events/club meetings
* User is able to directly message another student

**Optional Nice-to-have Stories/Additional Features**
* User can see suggested usernames
* User will be Warned user about deletion of course/notes
* User may have the option to save notes before class deletes
    * **TBD
* User may seek FAQ’s
    * Under editing profile
* User is able to use the app as a transfer student
* Users who are Professors have the ability to send notifications


### 2. Screen Archetypes

* Login Screen
    * User can log in
        * University email
        * Password
* Register Screen
    * User can register for an account for their associated college
        * First name
        * Last name
        * University email
        * Confirm university email 
        * Implement Verification
        * Password
        * Confirm Password
* Courses/Classes Fragment
    * User is able to see their enrolled classes
        * Add Classes (Several options for searching)
            * Professor Name
            * Class Name/Section
            * Building Name
            * Room Number
            * Time Start/End
        * Search Bar
            * Suggests classes
        * Remove Classes
            * Edit Menu - Select classes to delete
        * Semester organization

* Profile Fragment
    * User is able to view and edit their profile
        * Profile picture
        * Name
        * Major
        * Edit Profile
* Events/Activities Fragment
    * User is able to view the upcoming events and activities
* Messages Fragment
    * User is able to message students within their class
        * Direct messages
* Settings Screen
    * User is able to edit the settings of the app
        * Receive Notifications?
        * By Phone
            * banners
        * By Number
        * By E-mail

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Classes/Courses Tab
* Calendar Tab
* Messages Tab
* Profile Tab

**Flow Navigation** (Screen to Screen)

* Register Screen
    * Courses/Classes Fragment
* Login Screen
    * Courses/Classes Fragment
* Classes/Courses Tab
    * Courses/Classes Fragment
* Calendar Tab
    * Calendar Fragment
* Messages Tab
    * Messages Fragment
* Profile Tab
    * Profile Fragment

## Wireframes
<img src="https://github.com/improgo-josh/UniversityCrew/blob/master/UCrewPrototypeGIF.gif" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models
   | Property       | Type      | Description |
   | -------------  | --------  | ------------|
   | email          | String    | the user's university email address |
   | firstName      | String    | the user's first name |
   | lastName       | String    | the user's last name |
   | code           | String    | the user's verification code to login |
   | subject        | String    | the name of the subject/course to create/join |
   | professor      | String    | the professor's name of a subject/course |
   | building       | String    | the name of the building location |
   | room           | String    | the number and letter of the subject/course's location |
   | courseNumber   | int | the number of the subject/course (ex: PSYCH "101") |
   | amntUsers      | int | the number of users in a particular course |
   | hour_start     | int | the hour that the course begins |
   | hour_end       | int | the hour that the course ends |
   | min_start      | int | the minute that the course starts |
   | min_end        | int | the minute that the course ends |
   | picture        | File | the image in Events |
   | eventName      | String | the name of the event |
   | details        | String | the details of the event |
   | recentMsg      | String | the most recent message in a chat |
   | recentUser     | String | the most recent user in a chat |
   | timeMsg        | DateTime | the time that the message was posted |
   | file           | File | the file that a user may post |

### Networking
    - Login Screen
        - (Read/GET) Retrieve email and password to ensure an account is created with a university email.
    - Register Screen
        - (Create/PUT) Create an name, password, and email.
    - Verification Screen
        - (Get) Send a code to a university email for verification.
    - Courses
        - (Create/COURSE) Create a new course and post it
        - (Read/GET) Retrieve a currently created course
        - (Delete) Leave a course that the user is enrolled in
        - (Read/GET) Query All messages from a classroom/course.
        - (Read/GET) Query courses from 
    - Profile
        - (Read/GET) Query logged in user
    - Events
        - (Create/EVENT) Create a new event and post it
    - Messages
        - (Read/GET) Retrieve messages from a certain user.
        - (Create/PUT) Send messages to a certain user.
        - (Create) Create a Coversation with a certain user.
        - (Delete) Delete/Block a Coversation with a certain user.
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
