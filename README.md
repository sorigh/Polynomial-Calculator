# Polynomial-Calculator
This project implements a Polynomial Calculator with a graphical user interface (GUI). Users can input two polynomials, select a mathematical operation to perform between them, and view the results in an intuitive and user-friendly interface. The application is designed with Object-Oriented Programming (OOP) principles to ensure scalability and maintainability.

##Application Structure

###Classes
- Polinom: Represents a polynomial using a HashMap to store monomials. Methods for parsing, displaying, and manipulating polynomials.

- Operation: Static class for performing operations between polynomials. Includes methods for addition, subtraction, multiplication, division, derivation, and integration.

- Display: Manages the GUI using Swing. Handles user interactions, input processing, and result display.

###Algorithms

- Parsing: Converts a polynomial from a string input into monomials using regular expressions.
- Operations: Implements mathematical operations efficiently, e.g., multiplication with nested loops and division using long division logic.

###Testing

- Comprehensive unit tests using JUnit to ensure correctness and reliability. Test coverage exceeds 90% for critical classes and methods.
