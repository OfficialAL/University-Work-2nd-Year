# AI Coding Instructions for Object-Oriented Programming Labs

## Project Overview
This is a university-level Java Object-Oriented Programming course workspace containing multiple lab exercises (Lab1, Lab2, Lab3). Each lab focuses on core OOP concepts with self-contained exercises demonstrating progressively complex Java patterns.

## Key Architecture Patterns

### Lab Structure Convention
- Each lab has its own directory with dedicated `src/` and `bin/` folders
- Source files use either package structure (`lab1/`) or default package (Lab2, Lab3)
- Compiled output goes to `bin/` directory via VS Code Java extension
- Lab submission folders follow naming: `Lab X Submission A Rush` format

### Self-Testing Pattern
**Critical**: All main classes include comprehensive embedded testing in their `main()` methods rather than separate test files. This is the standard pattern across all labs.

Example from `VendingMachine.java`:
```java
public static void main(String[] args) {
    System.out.println("=== VENDING MACHINE COMPREHENSIVE TESTS ===");
    
    // Test 1: Initial balance is zero
    VendingMachine vm1 = new VendingMachine();
    System.out.println("PASS: " + (vm1.getBalance() == 0));
    
    // Multiple test scenarios with detailed output...
}
```

### Object-Oriented Design Patterns
- **Encapsulation**: Private fields with public getter/setter methods (e.g., `VendingMachine` class)
- **Static Constants**: Use `static final` for configuration values (`CANDY_PRICE`, `CANDY_CAPACITY`)
- **State Management**: Objects maintain internal state with validation (inventory tracking, balance management)

## Development Workflow

### Running Code
- Use VS Code's Java extension "Run" button or terminal execution
- Each class with `main()` method is independently executable
- No external build tools required - relies on VS Code Java project settings

### Key VS Code Configuration
Located in `.vscode/settings.json`:
```json
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin", 
    "java.project.referencedLibraries": ["lib/**/*.jar"]
}
```

### Testing Approach
- **No JUnit**: Use embedded testing in `main()` methods with boolean assertions
- **Output Format**: Clear test descriptions with PASS/FAIL indicators
- **Test Structure**: Initialize→Act→Assert→Display Results pattern

## Project-Specific Conventions

### Code Style
- Field declarations without explicit access modifiers (package-private by default)
- Constructor initialization of all instance variables
- Comprehensive `toString()` methods for debugging
- Static factory methods for constants access

### Lab Exercise Patterns
- **Lab1**: Basic syntax, control structures, variable types
- **Lab2**: Geometric shapes with vector mathematics (`Vector`, `Rectangle`, `Circle`)
- **Lab3**: State machines and business logic (`VendingMachine`, `QueryString`)

### Stub Method Convention
Incomplete methods use comment `// stub method` with `return null;` or appropriate default:
```java
String getParameter(String name) {
    // stub method
    return null;
}
```

## Integration Points
- Pure Java with no external dependencies
- Standard Java library usage only
- VS Code Java Extension for compilation and execution
- Windows PowerShell terminal for command execution

## When Working on This Codebase
1. **Always include comprehensive testing** in the main method following the established pattern
2. **Maintain the encapsulation pattern** with private fields and public accessors
3. **Use descriptive test output** with clear PASS/FAIL indicators and expected vs actual values
4. **Follow the lab naming conventions** for consistency with existing structure
5. **Implement complete functionality** rather than leaving stub methods when adding features