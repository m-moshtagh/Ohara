# Speed Reading for code

Reading code is a larger part of a programmer’s working life than you might realize. Research indicates that almost 60% of programmers’ time is spent understanding rather than writing code.

## Quickly reading Code

The book Structure and Interpretation of Computer Programs by Harold Abelson, Gerald Jay Sussman, and Julie Sussman (MIT Press, 1996) contains this wellknown sentence:

> “Programs must be written for people to read and only incidentally for machines to execute.”

Comments are, of course, often used to describe code that is already written, but as you can see in this example, they have diverse uses and can also be used as a memory aid for future code.

### Why is reading unfamiliar code hard?

The most crucial reason is the limited capacity of your STM.

## Overcoming size limits in your memory

### The power of chunking

The chess expriment between two group.

The finding that it is easier to remember when you have a lot of knowledge about something in your LTM also holds in programming.

#### What happens when information enters brain

There is a stage that information passes through before it reaches STM, called sensory memory. Which works as a IO buffer.

#### Iconic Memory

When reading code, information comes in through your eyes, after which it is briefly stored in the iconic memory.

As you just learned, everything you read is first stored in your iconic memory. But not everything that your iconic memory stores can be processed by your STM, so when you read code in detail, you have to make choices about what you can process.

#### Design patterns

Knowing design patterns in the efficient way can improve the chunking code more efficiently. It also helps mainatain code more easily because the mind knows the patterns.

#### Write comments

Researchs show that adding comments make programmers to read code slower however, This isn't a downside, novice programmers rely on comments.
It also improves the maintainability of code if the comments are **High Level**

#### Leve beacons

A final thing you can do to make the process of chunking code easier is to include beacons. Beacons are parts of a program that help a programmer understand what the code does.
Beacons typically indicate that a piece of code contains certain data structures, algorithms, or approaches.

These things are determind by:

- variable names
- comments
- String literals

> Beacons provide an important signaling service for programmers during the comprehension process because they often act as a trigger for programmers to confirm or refute hypotheses about the source.

We have two types of beacons:

- simple: self-explaining syntactic code elements, such as meaningful variable names, operators and if,else can be becaons too.
- compound:  Compound beacons are larger code structures comprised of simple beacons. Compound beacons provide semantic meaning for functions that simple beacons execute together. for example: class members which don't provice useful information on their own but, together they are meaningful for programmers are compound beacons. a for loop can also be a compound beacon.

### Approach

#### Step 1: Select code

Select an unfamiliar code base with familiar programming language

#### Step 2: Study code

Study the selected code and try to summarize the meaning of the code.

#### Step 3: Actively notice beacons that you use

Write a comment, note anything when we find a beacon which made us understand the code and check with expert.

#### Step 4: Reflect

We can reflect using these questions:

- What beacons have you collected?
- Are these code elements or natural language information?
- What knowledge do they represent?
- Do they represent knowledge about the domain of the code?
- Do they represent knowledge about the functionality of the code?

#### Step 5: Contribute back to the code (optional)

We can help improve the code beacons or provide better beacons.

#### Step 6: Compare with someone else (optional)

We can compare it with a coworker working on the same thing.

## Suammary

- The STM has a capacity of two to six elements.
- To overcome the size limitation, your STM collaborates with your LTM when you remember information.
- When you read new information, your brain tries to divide the information into recognizable parts called chunks.
- When you lack enough knowledge in your LTM, you have to rely on low-level reading of code, like letters and keywords. When doing that, you will quickly run out of space in your STM.
- When your LTM stores enough relevant information, you can remember abstract concepts like “a for-loop in Java” or “selection sort in Python” instead of the code at a lower level, occupying less space in your STM.
- When you read code, it is first stored in the iconic memory. Only a bit of the code is subsequently sent to the STM.
- Remembering code can be used as a tool for (self) diagnosis of your knowledge of coding. Because you can most easily remember what you already know, the parts of code that you remember can reveal the design patterns, programming constructs, and domain concepts you are most familiar with.
- Code can contain characteristics that make it easier to process, such as design patterns, comments, and explicit beacons.
