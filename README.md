# Cellphone (or Agenda Manager)📋

_This was the final project for the Subject Object Oriented Programming.
The idea was to make the Classes Diagram and then move it to the code._

## What we did 

_The code was made in Java, we used Singleton pattern on the "Gestor" class so it wouldn't instantiate more than one time._

_In order to comply with and enforce encapsulation, all "Gestor" methods are private and modularized so that only one class (the orchestrator) handles the logic and can access them._

_Using polymorphism and inheritance, we made some abstract methods on the Parent classes so the child ones were obligated to overwrite them._

_We also made a ContactRepository class that contains two methods:_

_- 1. saveContacts: it saves the List of Contacts in a .txt file_
_- 2. loadContacts: it loads the previous List of Contacts from the .txt file so the user doesn't lose data._
    
_Since we hadn't seen Swing we were not required to use UI but we made a Menu so the user can interact with the app through the terminal._

## Contributors ✒️

- **Agustin Gimenez** (Me)
- **Gonzalo Lema** - https://github.com/lema-23
