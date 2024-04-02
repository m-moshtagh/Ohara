# Algorithms

An algorithm is a set of instructions for accomplishing a task. Every piece of code could be called an algorithm.

## Binary Search

Binary search is an algorithm; its input is a sorted list of elements, If an element you’re looking for is in that list, binary search returns the position where it’s located. Otherwise, binary search returns null.
Things work out like using a dictionary.

> In general, for any list of n, binary search will take log2 n steps to run in the worst case, whereas simple search will take n steps.
> When you search for an element using simple search, in the worst case you might have to look at every single element. So for a list of 8 numbers, you’d have to check 8 numbers at most. For binary search, you have to check log n elements in the worst case. For a list of 8 elements, log 8 == 3, because 23 == 8. So for a list of 8 numbers, you would have to check 3 numbers at most. For a list of 1,024 elements, log 1,024 = 10, because 210 == 1,024. So for a list of 1,024 numbers, you’d have to check 10 numbers at most.

**Binary search only works when your list is in sorted order. For example, the names in a phone book are sorted in alphabetical order, so you can use binary search to look for a name.**

## Big O notation

Big O notation is special notation that tells you how fast an algorithm is.
This tells you the number of operations an algorithm will make. It’s called Big O notation because you put a “big O” in front of the number of operations.

> Big O notation is about the worst-case scenario. So you can say that, in the worst case, you’ll have to look at every entry in the phone book once.

Here are five Big O run times that you’ll encounter a lot, sorted from fastest to slowest:

- O(log n), also known as log time. Example: Binary search.
- O(n), also known as linear time. Example: Simple search.
- O(n * log n). Example: A fast sorting algorithm, like quicksort
- O(n2). Example: A slow sorting algorithm, like selection sort
- O(n!). Example: A really slow algorithm, like the traveling salesperson

### takeaways

- Binary search is a lot faster than simple search.
- O(log n) is faster than O(n), but it gets a lot faster once the list of items you’re searching through grows.
- Algorithm speed isn’t measured in seconds.
- Algorithm times are measured in terms of growth of an algorithm.
- Algorithm times are written in Big O notation.
