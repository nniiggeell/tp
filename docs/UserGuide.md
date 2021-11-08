---
layout: page
title: User Guide
---
## Introduction

NewAddressBook is a **desktop app built for Singapore secondary school teachers** that are handling large classes. It helps them **manage the contacts of their students and colleagues** efficiently. It also supports **keeping track of upcoming meetings** and **recording the medical histories** of students.

* Table of Contents
{:toc}

----------------------------------------------------------------------------------------------------------------
## Quick start

1. Ensure you have Java `11` or above installed on your computer.

2. Download the latest `NewAddressBook.jar` from [here](https://github.com/AY2122S1-CS2103-T16-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for NewAddressBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how NewAddressBook already contains some sample data. (Depending on your OS, you may need to launch the jar file using the `java -jar newaddressbook.jar` command)<br>
   <br>
   ![Ui](images/Ui.png)

5. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

  * **`listStudent`** : Lists all students.

  * **`student n/John Doe p/98765432 e/johnd@example.com g/M a/311,
    Clementi Ave 2, #02-25 f/3E1 em/999 i/Math class t/naughty`** : Adds a student named "John Doe" to NewAddressBook.

  * **`deleteStudent 3`** : Deletes the 3rd student shown in the currently displayed student list.

  * **`clearStudent`** : Deletes all displayed students.

  * **`exit`** : Exits the app.

Refer to the [Overview](#overview) section for a summary of commands available and the  [Features](#features) section for details of each command.

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** If you face any issues launching NewAddressBook, go to the `data` folder, delete `newaddressbook.json`
and try launching again.
</div>

--------------------------------------------------------------------------------------------------------------------

## Overview

### User Interface (UI)
The following shows the different parts of NewAddressBook's main application window:

![MainUi](images/MainUi.png)

* The **Command Box** is where the user types in commands for execution.
* The **Result Box** displays the result of the commands executed.
* The **Student List** displays the list of students stored in the app.
* The **Teacher List** displays the list of teachers stored in the app.
* The **Data Source** displays information about where the app saves data.

#### Individual Student Display
The following image shows the various aspects that describe a student:

![StudentUi](images/StudentUi.png)

<div markdown="span" class="alert alert-primary">
:information_source: The **Medical History** field will only appear if you have added medical history for that student. 
Otherwise, it will not be shown. This allows the user to observe whether a student has any medical history easily.
When the medical history is too long or cannot be shown fully, it will be truncated with "...". To view the full 
medical history, use the `showMedical` command. 
</div>

#### Individual Teacher Display
The following image shows the various aspects that describe a teacher:

![TeacherUi](images/TeacherUi.png)

#### Meeting Window

Meetings are not displayed in the main window. Instead, they are displayed in a separate window, called the **Meeting window**.

The meeting window pops up when the user enters the `showMeeting` command in the main window's input box. Alternatively, the user may opt to click on the `Show Meetings` button in the menu bar.

The following image shows the meeting window:

![MeetingWindowUi](images/MeetingWindowUi.png)

The meeting window has its own input box, result box, and a list displaying all meetings.

#### Individual Meeting Display

The various fields that describe a meeting are as follows:

![MeetingUi](images/MeetingUi.png)

### Commands Summary

<div markdown="span" class="alert alert-primary">
:information_source: 
Out of all the commands, the only command which executes in both windows is the `undo` command. 
The other commands work **either** in the main application window or the meeting window, but not both. 
The `Window` column of the table below indicates which window the command is compatible with.
</div>

Action | Format | Window
--------|---------|----------
**Add student** | `student n/NAME p/PHONE e/EMAIL g/GENDER a/ADDRESS f/FORM_CLASS em/EMERGENCY_CONTACT i/INVOLVEMENT [t/TAG]…​` | Main
**Clear students** | `clearStudent` | Main
**Copy a field from students** | `copyStudent c/FIELD_TO_COPY` | Main
**Delete a student** | `deleteStudent INDEX` | Main
**Edit a student** | `editStudent INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [i/INVOLVEMENT] [a/ADDRESS] [f/FORM_CLASS] [em/EMERGENCY_CONTACT] [t/TAG]…​` | Main
**Find a student by name** | `findStudent KEYWORD [MORE_KEYWORDS]` | Main
**Filter a student** | `filterStudent [INVOLVEMENT] [t/TAG]…​` | Main
**List all students** |`listStudent` | Main
**Record a student's medical history** | `medical INDEX m/MEDICAL_HISTORY` | Main
**Show a student's full medical history** | `showMedical INDEX` | Main
**Add teacher** | `teacher n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER o/OFFICE_TABLE_NUMBER i/INVOLVEMENT [t/TAG]…​` | Main
**Copy a field from teachers** | `copyTeacher c/FIELD_TO_COPY` | Main
**Clear teachers** | `clearTeacher` | Main
**Delete a teacher** | `deleteTeacher INDEX` | Main
**Edit a teacher** | `editTeacher INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [i/INVOLVEMENT] [o/OFFICE_TABLE_NUMBER] [t/TAG]…​` | Main
**Find a teacher by name** | `findTeacher KEYWORD [MORE_KEYWORDS]` | Main
**Filter a teacher** |`filterTeacher [INVOLVEMENT] [t/TAG]…​` | Main
**List all teachers** | `listTeacher` | Main
**Exit** | `exit` | Main
**View help** | `help` | Main
**Open meetings window** | `showMeeting` | Main
**Add meeting** | `meet r/TITLE d/DATE_TIME v/VENUE w/ATTENDEE_TYPE` | Meetings
**Remove meeting** | `deleteMeeting INDEX` | Meetings
**Clear all meetings** | `clearMeeting`  | Meetings
**Close meetings window** | `quitMeeting` | Meetings
**Undo**| `undo` | Both

--------------------------------------------------------------------------------------------------------------------

## Glossary
* **Involvement**: refers to how the user is involved with the student/teacher contact.
  This is a broad term describing why the user would even want to bother storing the student/teacher in
  NewAddressBook in the first place. For example, a math teacher might store the involvement of a student in their
  class as `in my math class`. A CCA teacher might store the involvement of a student in his badminton CCA as `badminton`.
  A teacher involved in an overseas CIP trip might want to store the involvement of students going along with him as `CIP trip`.

* **Currently displayed list**: For students, for example, there are two lists that are maintained by the application. One is the full list of students. The other is the list which the user currently sees on screen. For various reasons, the list the user sees may not be the same as the full list of students (perhaps the user executed a `findStudent` command) . When we say "currently displayed list" we mean the list that is currently shown to the user.

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `student` command, `NAME` under `n/NAME` represents the student's name that you should provide.
  For instance, if the student's name is "John Doe", you should input `n/John Doe` in the `student` command.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as `t/friend`, `t/friend t/family` etc.

* For most commands except `filterStudent` and `filterTeacher`, parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. for the `help` command, if you specify `help 123`, it will be interpreted as `help`. For other commands that require parameters, the input format must be as stated in the user guide.

* For all prefixes (except the first), the user should ensure they are preceded by a space for the parser to recognise it as a new prefix, instead of a parameter in the previous prefix. For example, `editStudent 1 i/e/hello@example.com` parsers the parameter for `i/` as `e/hello@example.com`, and does not detect any input for the `e/` field. On the other hand, for the command `editStudent 1 i/ e/hello@example.com`, `i/` is detected to have no arguments, while `e/` has `hello@example.com` as an argument. As such, the second command will not succeed since the `i/` field is blank. 

* For all parameters, extraneous spaces within the input values will not be trimmed.
  e.g. `n/john doe` is considered different from `n/john     doe`

* All parameters and commands, unless specified, are case-sensitive.
</div>

The commands offered can be roughly split into 4 categories: those involving students, teachers, meetings, and general commands.

1. [Managing Student Contacts](#managing-student-contacts)
- [Add a student](#add-a-student--student)
- [Clear student contacts](#clear-student-contacts--clearstudent)
- [Copying fields from students](#copying-fields-from-students--copystudent)
- [Delete a student](#delete-a-student--deletestudent)
- [Edit a student](#edit-a-student--editstudent)
- [Find students by name](#find-students-by-name--findstudent)
- [Filter students](#filter-students--filterstudent)
- [List all students](#list-all-students--liststudent)
- [Modify medical history of a student](#modify-medical-history-of-a-student--medical)
- [View the full medical history of a student](#view-the-full-medical-history-of-a-student--showmedical)

2. [Managing Teacher Contacts](#managing-student-contacts)
- [Add a teacher](#add-a-teacher--teacher)
- [Clear teacher contacts](#clear-teacher-contacts--clearteacher)
- [Copying fields from teachers](#copying-fields-from-teachers--copyteacher)
- [Delete a teacher](#delete-a-teacher--deleteteacher)
- [Edit a teacher](#edit-a-teacher--editteacher)
- [Find teachers by name](#find-teachers-by-name--findteacher)
- [Filter teachers](#filter-teachers--filterteacher)
- [List all teachers](#list-all-teachers--listteacher)

3. [Managing Meetings](#managing-meetings)
- [Add a meeting](#add-a-meeting--meet)
- [Delete a meeting](#delete-a-meeting--deletemeeting)
- [Clear all meetings](#clear-meetings--clearmeeting)
- [Show meetings window](#show-meetings-window--showmeeting)
- [Quit meetings window](#quit-meetings-window--quitmeeting)

4. [General](#general)
- [Exiting the program](#exiting-the-program--exit)
- [Undo the latest change](#undo-the-latest-change--undo)
- [Viewing help](#viewing-help--help)

### Managing Student Contacts

#### Add a student : `student`

Adds a student to NewAddressBook.

Format: `student n/NAME p/PHONE e/EMAIL g/GENDER a/ADDRESS f/FORM_CLASS em/EMERGENCY_CONTACT
i/INVOLVEMENT [t/TAG]…​`

Parameters:
* `NAME` The name of the student
  * should not be blank
  * should only contain alphanumeric characters and spaces

* `PHONE` The phone number of the student
  * should only contain numbers, and it should be at least 3 digits long

* `EMAIL` The email of the student
  * should not be blank
  * should be of the format `local-part@domain`
  * `local-part` should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-)
  * `domain` should be at least 2 characters long, start and end with alphanumeric characters, and only contain alphanumeric characters, periods and hyphens

* `GENDER` The gender of the student
  * must be one of the following: `M` (Male), `F` (Female) or `N` (Non-binary)
  * case-insensitive

* `ADDRESS` The address of the student
  * should not be blank

* `FORM_CLASS` The form class that the student belongs to
  * should not be blank
  * should be of the format `|LEVEL|STRING|[ALPHANUMERIC]|`
  * `LEVEL` must be a digit from 1 to 5
  * `STRING` should not be blank and can only contain alphabets
  * `ALPHANUMERIC` is optional and can only contain alphanumeric characters
  * For example, `4E1` is allowed but `41` is not allowed

* `INVOLVEMENT` The user's main involvement with the student
  * should not be blank

* `EMERGENCY_CONTACT` The emergency contact number of the student
  * should only contain numbers, and it should be at least 3 digits long

* `TAG` A tag associated with the student
  * should only contain alphanumeric characters
  * if the tag field is specified, it cannot be empty

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A student can have any number of tags (including 0). However, if the same tag is given more than once, e.g. `t/cat t/cat`,
only one will be displayed as they are considered the same.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Check out how `INVOLVEMENT` is intended to be used in the glossary.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook prevents the user from adding in duplicate students. Two students are duplicate if they have the same name and address.
However, this duplicate check is currently case-sensitive. For example, NewAddressBook will treat "John" and "john" as two
different names as they have different casing. We will improve on this in future versions of NewAddressBook to allow this duplicate
check to be case-insensitive. 
</div>


<div markdown="span" class="alert alert-info">:information_source: **Notes about `FORM_CLASS`:**
While the format allowed for `FORM_CLASS` caters to most secondary schools in Singapore, we understand that some schools 
have other naming conventions for their classes. We will work on this in future versions of NewAddressBook to allow for more flexibility.
</div>

Examples:
- `student n/James p/94629424 e/j77@example.com a/George street, block 123, #01-01 f/3A2 g/M i/Math class em/92696977`
- `student n/Betsy Crowe p/83958294 e/bc33@example.com a/Adams road, block 8, #03-05 f/3C1 g/F i/Bio rep em/96122134 t/President t/exco`

#### Clear student contacts : `clearStudent`

Clears all **currently displayed** students from NewAddressBook. If the currently displayed list is empty, the application warns the user that the list is empty and nothing is cleared.

Format: `clearStudent`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you want to delete all students from NewAddressBook, simply make sure the currently displayed list contains all students. You can make all students appear in the displayed list by the `listStudent` command. 
</div>

Example:
* `listStudent` followed by `clearStudent` clears all students from NewAddressBook.
* `filterStudent t/smart` followed by `clearStudent` clears all students with the "smart" tag from NewAddressBook.

#### Copying fields from students : `copyStudent`
Copy specified data from all students in the **currently displayed** student list. The fields that can be copied are:

- email
- phone numbers
- name

Format: `copyStudent c/FIELD_TO_COPY`

* `FIELD_TO_COPY` can only be one of the three: `phone`, `email` or `name`. They are case sensitive.

Example:
* `listStudent` followed by `copyStudent c/email` copies the emails of all students in NewAddressBook to the user's clipboard.
* `findStudent Betsy` followed by `copyStudent c/phone` copies the phones of students whose name matches Betsy. The definition of "matches" here uses the definition in the `findStudent` command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the currently displayed student list is empty, nothing will be copied to the clipboard.
</div>

#### Delete a student : `deleteStudent`

Deletes the specified student from NewAddressBook.

Format: `deleteStudent INDEX`

* Deletes the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the **currently displayed** student list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of **the currently displayed student list**.

Examples:
* `listStudent` followed by `deleteStudent 2` deletes the 2nd student in NewAddressBook.
* `findStudent Betsy` followed by `deleteStudent 1` deletes the 1st student in the results of the `findStudent` command.

#### Edit a student : `editStudent`

Edits an existing student in NewAddressBook.

Format: `editStudent INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [i/INVOLVEMENT] [a/ADDRESS]
[f/FORM_CLASS] [em/EMERGENCY_CONTACT] [t/TAG]…​`

* Edits the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the **currently displayed** student list. The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the **currently displayed** student list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can remove all the student’s tags by typing `t/` without specifying any tags after it.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If editing the student causes NewAddressBook to have duplicate students, the user will not be allowed to edit the student.
</div>

Examples:
*  `editStudent 1 f/4Donkey e/johndoe@example.com` Edits the form class and email address of the 1st student in the currently displayed student list to be `4Donkey` and `johndoe@example.com` respectively.
*  `editStudent 2 em/901941341` Edits the emergency contact number of the 2nd student in the currently displayed student list to be 901941341.

#### Find students by name : `findStudent`

Finds students whose names contain any of the given keywords.

Format: `findStudent KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Students matching **at least one keyword** will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findStudent John` returns `john` and `John Doe`
* `findStudent alex Yu` returns `Alex Yeoh`, `Bernice Yu`


#### Filter students : `filterStudent`

The user can filter students by:
- tag(s)
- involvement

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The `filterStudent` command differs from the `findStudent` in that `findStudent` only finds students by **name**, whereas the `filterStudent` command allows the user to filter out students based on **tag(s) and/or involvement**.
</div>

Format:`filterStudent [INVOLVEMENT] [t/TAG]…​`

* It allows filtering by either `TAG` or `INVOLVEMENT` or both.
* Either `INVOLVEMENT` or `TAG` must be present.
* The search for both tag and involvement is case-insensitive. e.g `hans` will match `Hans`.
* The search for both tag and involvement matches substrings, e.g `han` will match `Hans`.
* Involvement searches are broken up. That is, if the command was `filterStudent One Two`, `One Two` be broken up into two strings `One` and `Two` and it will search for involvement that contains both `One` and `Two`. Similarly, if the user executes `filterStudent one one`, then the command treats it as though a single `one` was put in. Similarly, an input with `t/tag1 t/tag1` is treated as though the user had put in a single `t/tag1`.
* Students matching **all** of the search will be returned (i.e. `AND` search). For example, if the search was `filterStudent chess club t/member`, only students whose involvement is `chess club` **and** has tags containing `member` will be returned.
* Only alphanumeric tag parameters in the search are allowed.
* Involvement must come before tag. e.g. `filterStudent chess club t/member` is allowed but `filterStudent t/member chess club` is not.


Examples:
- `filterStudent class t/rep` - will return all students with the involvement containing `class` and has at least one tag containing `rep`.
- `filterStudent math class` - will return all students with the involvement containing `math class`.
- `filterStudent t/banana t/phone` - will return all students with tags containing “banana” and "phone".

#### List all students : `listStudent`

Shows a list of all students stored in NewAddressBook.

Format: `listStudent`

#### Modify medical history of a student : `medical`
Format: `medical INDEX [m/MEDICAL_HISTORY]`

* Adds the medical history to the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.

How this command works:
* Adds a medical history to an existing student if the student does not have any existing medical history.
* Overwrites the medical history of a student if the student already has an existing medical history.
* Removes the medical history of a student if the student already has an existing medical history and an empty `MEDICAL_HISTORY` is given.
* The command does not allow the user to overwrite the current `medicalHistory` with an identical `medicalHistory`.
  In other words, the new `medicalHistory` must be different from the existing `medicalHistory`.

Examples:
* `medical 1 m/ADHD`

#### View the full medical history of a student : `showMedical`

Displays a pop-up window for the user to view the full medical history of the student in NewAddressBook.

Format: `showMedical INDEX`

* View the full medical history of the student at the specified `INDEX`.
* `INDEX` refers to the index number shown in the **currently displayed** student list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed student list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the student has no medical history, the pop-up window will simply show the name of the student.
</div>

Examples:
* `showMedical 1`

### Managing Teacher Contacts

#### Add a teacher : `teacher`

Adds a teacher to NewAddressBook.

Format: `teacher n/NAME p/PHONE_NUMBER e/EMAIL g/GENDER o/OFFICE_TABLE_NUMBER
i/INVOLVEMENT [t/TAG]…​`

Parameters:
* `NAME` The name of the teacher
  * should not be blank
  * should only contain alphanumeric characters and spaces

* `PHONE` The phone number of the teacher
  * should only contain numbers, and it should be at least 3 digits long

* `EMAIL` The email of the teacher
  * should not be blank
  * should be of the format `local-part@domain`
  * `local-part` should only contain alphanumeric characters and these special characters, excluding the parentheses, (+_.-)
  * `domain` should be at least 2 characters long, start and end with alphanumeric characters, and only contain alphanumeric characters, periods and hyphens

* `GENDER` The gender of the teacher
  * can only be one of  the following: `M` (Male), `F` (Female) or `N` (Non-binary)
  * case-insensitive

* `OFFICE_TABLE_NUMBER` The teacher's table number in the school office
  * should only contain numbers. It should have at least 1 digit and at most 5 digits.
  * two office numbers are different if **all** their digits are the same, rather than just being the same number. For example, `001` is different from `1`. This is to provide users with more flexibility.

* `INVOLVEMENT` The user's involvement with the teacher
  * should not be blank

* `TAG` A tag associated with the teacher
  * should only contain alphanumeric characters
  * if the tag field is specified, it cannot be empty

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A teacher can have any number of tags (including 0). However, if the same tag is given more than once, e.g. `t/cat t/cat`,
only one will be displayed as they are considered the same.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Check out how `INVOLVEMENT` is intended to be used in the glossary.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook prevents the user from adding in duplicate teachers. Two teachers are the same if they have the same name and office table number. 
However, this duplicate check is currently case-sensitive. For example, NewAddressBook will treat "John" and "john" 
as two different names as they have different casing. We will improve on this in future versions of NewAddressBook 
to allow this duplicate check to be case-insensitive.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook allows two teachers to have the same office table number if the user wants to continue storing the
contacts of a teacher who has resigned. This way, a new teacher who occupies the same table can be added.
</div>

Examples:
- `teacher n/Messi p/94629424 e/j77@example.com o/12 g/M i/Math Department`
- `teacher n/Eden p/83958294 e/bc33@example.com o/15 g/N i/Class 3D Co-form t/buddy t/colleague`

#### Clear teacher contacts : `clearTeacher`

Clears all **currently displayed** teachers from NewAddressBook. If the currently displayed list is empty, the application warns the user that the list is empty and nothing is cleared.

Format: `clearTeacher`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you want to delete all teachers from NewAddressBook, simply make sure the currently displayed list contains all teachers. You can make all teachers appear in the displayed list by the `listTeacher` command. 
</div>

Example:
* `listTeacher` followed by `clearTeacher` clears all teachers from NewAddressBook.
* `filterTeacher t/good` followed by `clearTeacher` clears all teachers with the "good" tag from NewAddressBook.

#### Copying fields from teachers : `copyTeacher`
Copy specified data from all teachers in the **currently displayed** teacher list. The fields that can be copied are:

- email
- phone numbers
- name

Format: `copyTeacher c/FIELD_TO_COPY`

`FIELD_TO_COPY` can only be one of three strings: `phone`, `email` or `name`.

Example:
* `listTeacher` followed by `copyTeacher c/email` copies the emails of all teachers to the user's clipboard.
* `findTeacher Betsy` followed by `copyTeacher c/phone` copies the phones of teachers whose name matches Betsy. The definition of "matches" is as per the definition in the `findTeacher` command.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If the currently displayed teacher list is empty, nothing will be copied to the clipboard.
</div>

#### Delete a teacher : `deleteTeacher`

Deletes the specified teacher from NewAddressBook.

Format: `deleteTeacher INDEX`

* Deletes the teacher at the specified `INDEX`.
* `INDEX` refers to the index number shown in the **currently displayed** teacher list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of **the currently displayed teacher list**.

Examples:
* `listTeacher` followed by `deleteTeacher 2` deletes the 2nd teacher in NewAddressBook.
* `findTeacher Betsy` followed by `deleteTeacher 1` deletes the 1st teacher in the results of the `findTeacher` command.

#### Edit a teacher : `editTeacher`

Edits an existing teacher in NewAddressBook.

Format: `editTeacher INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [g/GENDER] [i/INVOLVEMENT] [o/OFFICE_TABLE_NUMBER] [t/TAG]…​`


* Edits the teacher at the specified `INDEX`.
* `INDEX` refers to the index number shown in the **currently displayed** teacher list. The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the **currently displayed** teacher list.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the teacher will be removed, ie. adding of tags is not cumulative.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can remove all the teacher’s tags by typing `t/` without specifying any tags after it.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If editing the teacher causes NewAddressBook to have duplicate teachers, the user will not be allowed to edit the teacher.
</div>

Examples:
*  `editTeacher 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st teacher to be `91234567` and `johndoe@example.com` respectively.
*  `editTeacher 2 o/5 t/` Edits the office table number of the 2nd teacher to be 5 and clears all existing tags.

#### Find teachers by name : `findTeacher`

Finds teachers whose names contain any of the given keywords.

Format: `findTeacher KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Teachers matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findTeacher John` returns `john` and `John Doe`
* `findTeacher alex Yu` returns `Alex Yeoh`, `Bernice Yu`

#### Filter teachers : `filterTeacher`
The user can filter teachers by:
- tag(s)
- involvement

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The `filterTeacher` command differs from the `findTeacher` in that `findTeacher` only finds teachers by **name**, whereas the `filterTeacher` command allows the user to filter out teachers based on **tags and involvement**.
</div>

Format:`filterTeacher [INVOLVEMENT] [t/TAG]…​`

* It allows filtering by either `TAG` or `INVOLVEMENT` or both.
* Either `INVOLVEMENT` or `TAG` must be present.
* The search for both tag and involvement is case-insensitive. e.g `hans` will match `Hans`.
* The search is for both tag and involvement matches substrings, e.g `han` will match `Hans`.
* Involvement searches are broken up. That is, if the command was `filterTeacher One Two`, `One Two` be broken up into two strings `One` and `Two` and it will search for involvement that contains both `One` and `Two`. Similarly, if the user executes `filterTeacher one one`, then the command treats it as though a single `one` was put in. Similarly, an input with `t/tag1 t/tag1` is treated as though the user had put in a single `t/tag1`.
* Teachers matching **all** of the search will be returned (i.e. `AND` search). For example, if the search was `filterTeacher chess club t/coordinator`, only teachers whose involvement is `chess club` **and** has tags containing `coordinator` will be returned.
* Only alphanumeric tag parameters in the search are allowed.
* Involvement must come before tag. e.g. `filterTeacher chess club t/coordinator` is allowed but `filterTeacher t/coordinator chess club` is not.

Example:
- `filterTeacher class t/rep` - will return all teachers with the involvement containing `class` and has at least one tag containing `rep`.
- `filterTeacher math class` - will return all teachers with the involvement containing `math class`.
- `filterTeacher t/banana t/phone` - will return all teachers with tags containing `banana` and `phone`.

#### List all teachers : `listTeacher`

Shows a list of all teachers stored in NewAddressBook.

Format: `listTeacher`

### Managing Meetings

NewAddressBook also allows you to keep track of upcoming meetings with parents, teachers or students.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook automatically removes meetings that have expired, so the user does not have to worry about deleting them!
</div>

#### Add a meeting : `meet`

Format: `meet r/TITLE d/DATE_TIME v/VENUE w/ATTENDEE_TYPE`

Parameters:
* `TITLE` A brief summary of the meeting.
  * should not be blank
* `DATE_TIME` A valid date and time of the meeting.
  * should be of the format `YYYY-MM-DD HH:mm`
  * should be a **valid** date in  the future
  * should pad the input with zeroes if the year, month or day is not two digits. For example, the date `2045-7-12` is invalid, but `2045-07-12` is valid.
  * the year should be exactly 4 digits long, month 2 digits, and day 2 digits. Hence, while `12045-01-01` is a valid date, it will not be accepted.
  * the time should be a valid 24h time. Again, the user should pad the time such that the hour and minutes are both exactly 2 digits long. For example, `2:45` is invalid, but `02:45` is valid.
* `VENUE`: The venue of the meeting.
  * should not be blank
* `ATTENDEE_TYPE`: The type of person(s) you are meeting with.
  * must be one of the following: `S` (students), `T` (teachers), `P` (parents)
  * case-insensitive

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can only add meetings in the future.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
NewAddressBook will prevent you from adding a meeting if the new meeting clashes (exact same date and time) with an existing meeting.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
If you provide an invalid date, the error message given by NewAddressBook is the same as when you provide an invalid date time format.
For example, "2022-02-29" is an invalid date since 2022 is not a leap year.
</div>

Example:
* `meet r/Meeting with Ms.Lee d/2040-07-12 14:08 v/Seminar room 3 w/P`

#### Delete a meeting : `deleteMeeting`

Deletes the specified meeting from NewAddressBook.

Format: `deleteMeeting INDEX`

* Deletes the meeting at the specified `INDEX`.
* `INDEX` refers to the index number shown in the displayed meeting list.
* The index **must be a positive integer** 1, 2, 3, …​ not exceeding the size of the displayed meeting list.

Examples:
* `deleteMeeting 2` deletes the 2nd meeting in NewAddressBook.
* `deleteMeeting 0` will return an error since the index is not positive.

#### Clear meetings : `clearMeeting`

Clears all meetings from NewAddressBook.

Format: `clearMeeting`

#### Show meetings window : `showMeeting`

Pops out the meeting window.

Format: `showMeeting`

#### Quit meetings window : `quitMeeting`

Closes the meeting window.

Format: `quitMeeting`

### General

#### Exiting the program : `exit`

Exits the program.

Format: `exit`

#### Undo the latest change : `undo`

Undoes the last change. This command only works for operations that adds, deletes, or edits information.
This means that commands such as `filterStudent/filterTeacher` and the `copyStudent/copyTeacher` command cannot be undone. One can undo adding/deleting meetings as well.

Format: `undo`

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** The `undo` command works in both the main and meeting window.
</div>

<div markdown="span" class="alert alert-primary">
:bulb: **Tip:** `undo` undoes the last action, **no matter** which window one executes it from. For example, if one edited a student in the main window, then added a meeting from the meeting window, executing the `undo` command from the main window will undo the action of **adding the meeting**, rather than the action of editing the student. Another way of putting it is that the `undo` command undoes the user's last action, rather than the user's last action **in that window**.
</div>

#### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

--------------------------------------------------------------------------------------------------------------------

## NewAddressBook Data File

### Saving the data

NewAddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

NewAddressBook data are saved as a JSON file `[JAR file location]/data/newaddressbook.json`. Advanced users are welcome to update data directly by editing the data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, NewAddressBook will discard all data and start with an 
empty data file at the next run. In the event that the modification you make causes NewAddressBook to stop functioning 
properly, please manually remove the data file and launch the app again.
</div>

--------------------------------------------------------------------------------------------------------------------
## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous NewAddressBook home folder.

--------------------------------------------------------------------------------------------------------------------
