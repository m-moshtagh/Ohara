# Why Rust?

Rust is a static language which was created to achieve high performance similar to C or C++ with prioritizing memory safety.
Benefits of rust:

* Large data processing
* concurrent programming
* Affective compiler
* Borrow Checker
* Reference Lifetime
* SafeRust & UnsafeRust
* Compile Time check on data races

Issues with rust:

* Slow compile time
* few libraries
* too many string types

Rust is a low level language. You can choose whether to store data on stack or heap.
Rust is a language designed for systems programming.

> RAII is also relevant. Means each time an object goes out of scope the destructor is called and resources are freed. This is not needed to be manual.

## Type safety

Rust protects our code from undefined behavior. For example C or C++ in C99 standard don't check if we declare an array `[0]` and store something in it like `[3]`. So Unknown behavior will occur in runtime. However, most languages are type safe and though Rust is a system language it is type safe. More details on Programming Rust book by Blandy & Orendorff

## Installation

The best way is to use rustup.rs. We can access several commands after installation.

### Cargo

Package manager and compilation manager for rust project.

### Rustc

Compiler of Rust

### RustDoc

Rust Documenting tool

```bash
cargo new --bin PROJECT_NAME
```

This generates a simple rust project and `--bin` resembles a rust executable instead of library.

Cargo creates a cargo.tml file which includes metadata for the package. We can write our dependencies in this file and cargo takes care of them.

### Hello world

```rust
fn main {
    println!("Hello rust buddy");
}
```

We simply run this via `cargo run` command. Cargo puts all executables inside /target directory.

> We can use `cargo clean` to clean the target directory.

## Syntax Notes

* Rust's functions are declared with `fn` keyword.
* codes wrapped with curly braces are called expressions. for example:

  * ```rust
    {
        println!("cos x:");
        x.cos()
    }
    ```
  
* We don't need `return` keyword for last statement that returns value of the function. We also don't need a `;`.
* if statements don't need parantheses but curly braces are mandatory.
* Rust can infer variables value.
* Rust attributes like `#[test]` are like java annotations. We can define our test functions and mark them with this attribute and assert using macros then execute `cargo test` to run tests.
