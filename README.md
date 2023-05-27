
# Jomini TCC Manager

This is a system to manage TCC's. It has an academic purpose and therefore records the information in a csv file. Designed for data structure discipline

# Technologies

Java (JDK 18) and Swing Library

# Path strucutre
```bash 
├───images (Image resources)
├───lib (Data structures resource)
└───src
    ├───components
    ├───constants
    ├───contracts
    ├───controller
    ├───model
    ├───service
    ├───threads
    └───view
  ```

### Any definitions:
#### components:
It is a custom component that extends from a Java Swing class. These components are reused in many screens, and a pattern has been created for them.

#### constants:
The name explains a lot. But basically, it's where we keep some data like: project name, version, where the files will be recorded and what the file format will be.
This helps because if you need to change the directory or the name of the project or the version, just go to the Configs.java file

#### contracts:
Where controller interfaces are grouped

#### service:
There is where we have the FileService, which is a class responsible for handling all the data, that is, it saves the file. There we also have one that helps with validations
