# Virtual Pets Amok

IMPORTANT: Please refer to the course in Instructure Canvas for project full requirements

*Oh no! Your virtual pet shelter has become a mess!*

*Food and water and playtime aren't enough anymore. The virtual cats are chasing the virtual dogs. The virtual organic animals are letting nature take its course in every corner, while the virtual robotic pets are getting rusty. Let's just say things are piling up.*

*Thanks to the generosity of donors, leashes aren't an issue, so you can walk all of your virtual dogs at once. This is great, since they are less like to soil their cages if walked regularly. Did I mention dogs are in cages now? Yeah, it keeps the cats from chasing them. Better keep those cages clean.*

*We're introducing a health attribute for pets. Organic pets have their health decreased by unclean circumstances, and by thirst or hunger growing too high. Robotic pets only lose health if they're not oiled regularly. All pets lose health if their happiness drops too low.*

## Required Tasks

### General

- introduce ways to track:
- [ ] cage cleanliness for organic dogs
- [ ] oil/maintenance level for robot animals

### All classes

- [ ] Encapsulate all instance variables.
- [ ] Assign appropriate visibility modifiers to methods only used internally by a class or within a class hierarchy.

### VirtualPet class and related subclasses/interfaces

In addition to the last project's requirements:

- [ ] create a model that allows for dogs and cats, either of which may be robotic
- [ ] introduce an attribute (instance variable) representing overall health that is updated as a result of other attributes moving in a negative or positive direction, influencing happiness
- introduce methods that allow for:
- [ ] oiling robotic pets
- [ ] walking dogs, decreasing the likelihood that organic dogs will soil their cages, while increasing happiness in all dogs
- do not allow:
- [ ] feeding/watering robotic pets
- [ ] oiling organic pets
- [ ] modify the `tick` method to return a value indicating the amount of waste a pet creates, which should contribute to either (optionally, create accessor methods to retrieve these values instead):
- [ ] amount of waste in an individual dog cage

### VirtualPetShelter class

In addition to the last project's requirements:

- introduce methods that allow for:
- [ ] oiling/maintaining all robotic pets
- [ ] cleaning dog cages
- [ ] modify existing methods to only feed/water pets that are *not* robotic

### VirtualPetShelterApp class

In addition to the last project's requirements:

- add options to:
- [ ] walk all dogs
- [ ] clean dog cages
- [ ] oil all robotic pets

## Tips

- Remember: don't get caught up in the user interface at first. Focus on shelter/pet behavior. Try to get one thing working at a time.
- Create tests to verify pet behavior, limiting your immediate goal to something small.

### Grading

Your grading will be based on three areas:

- Object Oriented Programming Practices
- Test Driven Development
- Working Software
- Clean Code
You need a *PASSING* on three of the four areas for an overall passing grade.

#### Object Oriented Programming Practices

- Abstraction - Your abstractions should help make your code easier to work with.  Think about separating high level concepts and low level concepts.
- Polymorphism - Use method overriding to allow objects of a supertype to make method calls with different behaviors.
- Inheritance - Use inheritance to keep concrete classes as simple as possible and remove duplicate code.  Use abstract superclasses and interfaces to define properties and behaviors that all subclasses will inherit.
- Encapsulation -  Proper use of visibility modifiers to ensure that elements that should be private are kept private.

We need to see demonstration of 3 of the 4 principles for this category to be considered PASSING.

#### Test Driven Development

You should write your code by writing tests first.  If you do so, each public method you write should have a unit test that covers its behavior.  The test classes should also be well maintained and follow the principles of clean code.  These are the things we are looking for with regards to Test Driven Development:

- 50% of your public methods (excepting the `main()` in your app class) should be covered by unit tests.
- All tests pass.

We are looking for adherence to all of the above areas for this category to be considered PASSING.

#### Working Software

Working software consists of does the application run and how well you met the requirements.  We are looking for your software to compile, a game loop to loop, and for 75% of the asked for requirements to be present to be considered PASSING.

#### Clean Code

Clean code deals with how your code is written.  Is it readable, easy to understand, formatted, and not littered with commented out code?   These are the things we will be looking for:

- Keep method chains to a minimum. (Three chained methods in the entire project are permissable.)
- Formatted code - This is the simplest task, use *CTRL + ALT + L* to have IntelliJ format your code for you.
- Variable and Method Names - Variables and method names should inform the reader what the purpose of the variable or method is.  A `feed()` method that affects the `hunger` field variable in your `VirtualPet` class is an example of good naming.
- If you use descriptive method and variable names, your reliance on comments can be reduced.  And you should never leave blocks of commented out code in your committed code.

We are looking for adherence to all four of the above principles for this category to be considered PASSING.

## Stretch Tasks

- Allow for other types of pets. Are they caged? What maintenance do they require?
- Allow an option for giving treats to pets. What sorts of treats do different pets like?
- introduce a `Cage` class for each dog, where cleanliness is tracked, keying these on the dog to which each is assigned (to use a `VirtualPet` as a key in a `HashMap`, you should implement `hashCode` and `equals`)
- *Cats hang out in a communal area and use a common litter box. Don't even bother trying to walk them. What happens if that litter box overflows? Does it reduce pets' health?*  Introduce a litterbox and a way to clean it for your organic cats.
- Herd your cats.

(*Note: the author is aware that it would not be a wise decision to allow all cats in a shelter to share a litterbox. They're virtual pets, so virtually disease-free.*)
