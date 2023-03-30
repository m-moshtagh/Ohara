# Decoding confusion while coding

Different types of confusion relate to different kinds of cognitive processes.

## Different kinds of confusion in code

### Confusion type 1: Lack of knowledge

`2 2 2 2 2 ⊤ n`

> You are not that familiar with APL and will not know the meaning of the operator T. Hence, the confusion here lies in a lack of knowledge.

### Confusion type 2: Lack of information

```Java
public class BinaryCalculator {
public static void mian(Integer n) {
System.out.println(Integer.toBinaryString(n));
}
}
```

> Based on the name of the method, you can guess the functionality. However, to deeply understand what the code does, you would need to navigate to the definition of toBinaryString() elsewhere in the code and continue reading there. Thus, the problem here is a lack of information. The information about exactly how toBinaryString() works is not readily available but needs to be found somewhere else in the code.

### Confusion type 3: Lack of processing power

```BASIC
LET N2 = ABS (INT (N))
LET B$ = ""
FOR N1 = N2 TO 0 STEP 0
    LET N2 = INT (N1 / 2)
    LET B$ = STR$ (N1 - N2 * 2) + B$
    LET N1 = N2
NEXT N1
PRINT B$
RETURN
```

> It’s too hard to hold all the intermediate values of the variables and the corresponding actions in your mind at the same time.

## Different cognitive processes that affect coding

- Lack of knowledge = Issue in LTM
- Lack of information = Issue in STM
- Lack of processing power = Issue in working memory

### LTM and programming

Your LTM stores several types of relevant programming information. It can, for example, store memories of when you successfully applied a certain technique, the meaning of keywords in Java, the meaning of words in English, or the fact that maxint in Java is 2147483647.

### STM and programming

STM is used to briefly hold incoming information. STM, which has a limited size. The estimates differ, but most scientists agree that just a few items fit in STM, and certainly not more than a dozen.

### Working memory and programming

The actual thinking, however, happens not in the LTM or STM, but in working memory. This is where new thoughts, ideas, and solutions are formed. If you think of the LTM as a hard drive and the STM as RAM, the working memory is best compared to the processor of the brain.

## Summary

- Confusion while coding can be caused by three issues: a lack of knowledge, a lack of easy-to-access information, or a lack of processing power in the brain.
- Three cognitive processes are involved when you read or write code.
- The first process is the retrieval of information from LTM, where the meaning of keywords is stored, for example.
- In the second process, information about the program at hand is stored in your STM, which can temporarily hold information like the name of a method or variable.
- The final process involved is the working memory. This is where processing of the code happens, for example, deciding that an index is one too low.
- All three cognitive processes are at work while you’re reading code, and the processes complement each other. For example, if your STM encounters a variable name like n, your brain searches your LTM for related programs you’ve read in the past. And when you read an ambiguous word, your working memory is activated and your brain will try to decide the right meaning in this context.
