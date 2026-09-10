# Truth Table Analyzer & Boolean Function Evaluator

## Description
This Java-based console application parses truth tables from a text file to process dynamic Boolean functions. It calculates the sum of minterms and the product of maxterms, and allows users to evaluate specific function results by interactively inputting custom variable values.

## Features
* **Dynamic File Parsing:** Automatically reads variables and functions from a text file named `dogruluk_tablosu.txt`. It supports a dynamic number of variables and functions (minimum 2 variables) represented by single characters[cite: 1].
* **Expression Generation:** Calculates and displays the sum of minterms (Σ) and the product of maxterms (Π) in three different forms, sorted in order[cite: 1].
* **Interactive Evaluation:** Prompts the user to input specific values for the boolean variables and dynamically computes the function's output[cite: 1].
* **Missing State Handling:** Identifies incomplete or missing rows in the provided truth table. If a user inputs variable values that correspond to a missing row, the application correctly evaluates and displays the result as undefined[cite: 1].
* **Object-Oriented Design:** Built with Object-Oriented Programming (OOP) principles using appropriate classes and methods[cite: 1].

## Technologies Used
* **Language:** Java[cite: 1]
* **Development Environment:** Eclipse[cite: 1]

## Input File Format
The program requires an input file named `dogruluk_tablosu.txt` located in the root directory[cite: 1]. 
* Variables and functions must be separated by the `|` symbol with a single space between each character[cite: 1].
* The row order can be mixed, and incomplete truth tables are supported[cite: 1].

**Example `dogruluk_tablosu.txt` structure:**
```text
x y z | f g
0 1 0 | 0 1
0 1 1 | 0 0
1 0 0 | 1 0
1 0 1 | 0 0
1 1 1 | 1 1
```
# Usage

Place the dogruluk_tablosu.txt file in the correct directory[cite: 1].

Run the compiled Java application.

The program will output the minterm and maxterm expressions automatically[cite: 1].

Enter the variable values when prompted to see the specific boolean result[cite: 1].


Development
Ömer ERTAN
