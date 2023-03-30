# Eucilid's Algorithm

In order to find Greates Common Divisor we can use this algorithm.

the full description is [Here](https://iq.opengenus.org/euclidean-algorithm-greatest-common-divisor-gcd/)

## Implementation

### Rust

```rust
fn main() {
    println!("gcd of 30 and 15 is: {}", gcd(30, 15));
}
fn gcd(number1: u64, number2: u64) -> u64 {
    if number1 == 0 {
        return number2;
    }
    if number2 == 0 {
        return number1;
    }
    gcd(number2, number1 % number2)
}

#[test]
fn test_gcd() {
    assert_eq!(gcd(30, 15), 15);
    assert_eq!(gcd(2 * 3 * 5 * 11 * 17,
         3 * 7 * 11 * 13 * 19), 3 * 11);
}
```
