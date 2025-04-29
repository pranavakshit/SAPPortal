
# SAPPortal - Student Management System

This is a Java-based Student Management System (SAPPortal) designed to manage student data and store it in a MySQL database. The project allows users to add, edit, delete, view, and truncate student records in the database. It is equipped with a graphical user interface (GUI) built using Java Swing.

## Features

- **Add New Student**: Allows you to add new student records to the system.
- **Edit Existing Student**: Edit existing student details.
- **Delete Student**: Remove student records from the database.
- **View All Students**: View a list of all the student records in the database.
- **Truncate Table**: Option to clear all student records in the table.
- **Delete Specific Records**: Allows for the deletion of selected records via checkboxes.

## Technologies Used

- **Java 23** (JDK 23)
- **Maven**: Build tool for managing dependencies and packaging the application
- **MySQL**: Database management system for storing student records
- **Swing**: GUI framework for building desktop applications
- **JUnit**: For testing Java code

## Prerequisites

- **Java 23 or higher**: Install Java on your machine. You can download it from [here](https://jdk.java.net/).
- **Maven**: A build tool for Java. To install Maven, follow the instructions [here](https://maven.apache.org/install.html).
- **MySQL**: A database system for storing student records. You can install MySQL [here](https://dev.mysql.com/downloads/installer/).

## Setup Instructions

### Step 1: Clone the Repository

Start by cloning the repository to your local machine:

```bash
git clone https://github.com/your-username/SAPPortal.git
cd SAPPortal
```

### Step 2: Set up MySQL Database

1. **Create a New Database**:

   Log in to MySQL and create a new database:

   ```sql
   CREATE DATABASE records;
   ```

2. **Create Tables**:

   Use the `schema.sql` file located in the `docs/db/` directory to create the necessary tables:

   ```sql
   USE records;
   -- Paste the content of schema.sql here
   ```

   The `schema.sql` file contains the structure for the `students` table and other related configurations.

### Step 3: Modify Database Connection

In the `DatabaseConnection.java` file, located in `src/main/java/com/pranav/sportal/db/`, modify the database connection details to match your setup:

```java
private static final String URL = "jdbc:mysql://127.0.0.1:3306/records"; // Modify this to your actual database
private static final String USER = "root"; // Your database username
private static final String PASSWORD = "root"; // Your database password
```

### Step 4: Build the Project

To build the project, run the following Maven command in the root directory of the project:

```bash
mvn clean install
```

This will download dependencies, compile the source code, and package the project.

### Step 5: Run the Application

Once the build is complete, you can run the application by executing the following Maven command:

```bash
mvn exec:java -Dexec.mainClass="com.pranav.sportal.gui.MainPanel"
```

This will launch the GUI where you can interact with the student records.

### Step 6: Test the Application

After launching the application, test the following functionalities:

- **Add New Student**: Click on the "Add Student" button and enter the student details.
- **Edit Existing Student**: Select a student and click "Edit" to modify details.
- **Delete Student**: Select a student and click "Delete" to remove them from the database.
- **Truncate Table**: Clear all records using the "Truncate Table" option.
- **Delete Specific Records**: Use checkboxes to select and delete specific student records.

## Screenshots

You can include a screenshot of the GUI here to showcase what the application looks like.

### Sample Screenshot

![Student Management System GUI](docs/screenshots/GUI-sample.png)

## Database ER Diagram

The ER diagram for the database structure can be found in the `docs/uml/class-diagram.puml` file. You can also find an additional ER diagram in `docs/uml/ER-diagram.puml`.

## Troubleshooting

- **Database Connection Error**: Ensure that MySQL is running, and that the `URL`, `USER`, and `PASSWORD` in the `DatabaseConnection.java` file match your database configuration.
- **GUI Not Launching**: Ensure all necessary dependencies are configured correctly in your `pom.xml` file. If you're getting compilation errors, make sure you have the right Java version installed.
- **Missing Files**: If any file is missing or you encounter any `FileNotFoundException`, make sure all the source files are in place as per the project structure.

## License

This project is licensed under the MIT License.

---

### Project Structure

The project follows a standard Maven directory structure:

```plaintext
SAPPortal/
├── .gitignore
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── pranav/
│   │   │           └── sportal/
│   │   │               ├── db/
│   │   │               │   └── DatabaseConnection.java
│   │   │               ├── gui/
│   │   │               │   └── MainPanel.java
│   │   │               ├── model/
│   │   │               │   └── Student.java
│   ├── test/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── pranav/
│   │   │           └── sportal/
│   │   │               ├── db/
│   │   │               │   └── DatabaseConnectionTest.java
│   │   │               ├── model/
│   │   │               │   └── StudentTest.java
├── docs/
│   ├── db/
│   │   └── schema.sql
│   ├── uml/
│   │   ├── class-diagram.puml
│   │   └── ER-diagram.puml
│   ├── screenshots/
│   │   └── GUI-sample.png
```

