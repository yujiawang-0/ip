# PRIME

> "Someday we'll all get to **choose** what we want to be. But not today." -- Optimus Prime ([source](https://www.reddit.com/r/transformers/comments/131eizt/what_is_your_favorite_optimus_prime_quote/))

Optimus Prime aids in remembering your tasks for you, so that you may free your mind from the shackles of remembering them. 

It is,
* mostly text-based
* relatively easy to learn
* ~~SUPER FAST to use~~ as fast as your processor is capable of 

# PRIME User Guide

PRIME is a **desktop app for managing ToDos, Deadlines and Events in your life**, optimised for use via a Command Line interface (CLI) while still having some of the benefits of a Graphical User Interface (GUI). 

* [Quick Start](#quick-start) 
* [Features](#features)
   *   [Say Hello : ```hi``` or ```hello```](#say-hello)
   *   [Add a Todo : ```todo```](#add-a-todo)
   *   [Add a Deadline : ```deadline```](#add-a-deadline)
   *   [Add an Event : ```event```](#add-an-event)
   *   [Listing the log : ```list```](#listing-the-log)
   *   [Edit an item : ```edit```](#editing--updating-an-item)
   *   [Deleting an item : ```delete```](#deleting-a-task)
   *   [Findind an item : ```find```](#finding-a-task)
   *   [Exit the program: ```bye``` or ```goodbye```](#exit-the-program)

## Quick Start!
1. Ensure you have **Java 17** or above installed in your Computer.\
   Mac users: Ensure you have the precise JDK version prescribed here.

2. Download the latest ```.jar``` file from [here]().

3. Copy the file to the folder you want to use as the home folder for Prime.

3. Open a command terminal, ```cd``` into the folder you put the jar file in, and use the ```java -jar addressbook.jar``` command to run the application.\
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.\
![	/Ui.png]

4. Type the command in the text box and press Enter to execute it. \
   e.g. typing ```list``` and pressing Enter will show the current tasks in the Log.


## Features
:on: :top: **Important Notes about the command format:**
* Words encased in ```<PLACEHOLDER>``` are the parameters to be supplied by the user.\
  e.g. in ```todo <DESC>```, ```<DESC>``` is a paramter which can be used as ```todo buy birthday gift for Susan```.
* Parameters should be supplied in a **fixed order**.\
  e.g. if the command specifies ```<DESC> /from <FROM_DATE> /to <TO_DATE>```, any other permuatation is **not** acceptable.

### Say hello
Say hello to Prime, who will greet you back.  
Format: ```hello``` or ```hi```


### Add a todo
Adds a ToDo to the log.  
Format: ```todo <DESC>```

Examples:  
* ```todo buy birthday gift for susan```
* ```todo complete CS2103T iP tasks```


### Add a Deadline
Adds a Deadline to the log.  
Format: ```deadine <DESC> /by <DUE_DATE>```

Examples:  
* ```deadline English essay /by 2025-10-31```
* ```deadline complete CS2103T tP tasks /by 2026-03-01```


### Add an Event
Adds a Event to the log.  
Format: ```event <DESC> /from <FROM_DATE> /to <TO_DATE>```

Examples:  
* ```event CNY dinner with family /from 2026-02-16 /to 2026-02-16```
* ```event NUS career fair /from 2026-02-06 /to 2026-02-08```


### Listing the log
Shows a list of all tasks currently logged (inclusive of marked tasks).  
Format: ```list```


### Editing / Updating an item
Edits an existing task in the log.  
Format: ```edit <INDEX> <FIELD> <NEW_VALUE>``` or ```edit <INDEX> <FIELD> <NEW_VALUE>```​

* Edits the task at the specified INDEX. The index refers to the index number shown in the log. The index must be a positive integer 1, 2, 3, … ​
* Field to be changed must be provided. The fields that can be changed are:
   * ```desc``` : update the task description (applicable for ToDos, Deadlines and Events)
   * ```by``` : update the BY_DATE of deadline tasks
   * ```from``` : update the FROM_DATE of event tasks
   * ```to``` : update the TO_DATE of event tasks
* Existing values will be updated to NEW_VALUE if it is valid for that field.

Examples:  
* ```update 1 desc family dinner``` where 1 is a todo
* ```update 2 by 2025-03-01``` where 2 is a deadline
* ```update 3 from 2026-02-14``` where 3 is an event

### Deleting a task
Deletes the specified task from the log.
Format: ```delete <INDEX>```

* Deletes the person at the specified INDEX.
* The index refers to the index number shown in the log. The index must be a positive integer 1, 2, 3, …​

Examples:
* ```delete 2``` deletes the 2nd task in the log.

### Finding a task 
Finds task whose description contain any of the given keywords.

Format: ```find <KEYWORD>```

* The search is case-insensitive. e.g ```ip``` will match ```iP```
* The order of the keywords **does** matter. e.g. ```ip submit``` will match **NOT** ```submit iP```
* **Only the description of the task is searched**.
* Tasks matching at least one keyword will be returned

Examples:
* ```find ip``` returns ```submit iP```, ```CS2103T week 5 iP tasks```.

### Exit the program
Exits Prime

Format: ```goodbye```or ```bye```

# Acknowlegdments
* JUnit testing codes in the ```test``` folder were generated by ChatGPT
