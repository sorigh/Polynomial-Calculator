# Polynomial-Calculator

A Java-based Polynomial Calculator with a graphical user interface (GUI) that allows users to input two polynomials, select mathematical operations (addition, subtraction, multiplication, division, derivation, and integration), and view the results. The calculator uses a HashMap data structure to store polynomial terms and performs efficient operations on them. The application also includes input validation with regular expressions and unit testing for accuracy.

## Application Structure

### Classes
- Polinom: Represents a polynomial using a HashMap to store monomials. Methods for parsing, displaying, and manipulating polynomials.

- Operation: Static class for performing operations between polynomials. Includes methods for addition, subtraction, multiplication, division, derivation, and integration.

- Display: Manages the GUI using Swing. Handles user interactions, input processing, and result display.

### Algorithms

- Parsing: Converts a polynomial from a string input into monomials using regular expressions.
- Operations: Implements mathematical operations efficiently, e.g., multiplication with nested loops and division using long division logic.

### Testing

- Comprehensive unit tests using JUnit to ensure correctness and reliability. Test coverage exceeds 90% for critical classes and methods.
