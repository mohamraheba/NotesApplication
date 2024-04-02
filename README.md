Video demonstration: https://youtu.be/QpenRcQiGhc?si=9zwCKdbXR11Pxkxn

This project serves as the backend component for a Note management application, providing the necessary infrastructure and functionalities to manage notes and categories via a RESTful API.
Whether you're a student, professional, or enthusiast, this application offers a robust infrastructure to create, manage, and search notes efficiently.

##Features:

Note Management: CRUD operations. Seamlessly create, read, update, and delete notes with ease. Each note includes fields for title, content, creation date, and optional categorization.

Category Management: CRUD operations. Organize your notes effortlessly with category management functionalities. Create, update, and delete categories to group related notes together, facilitating easy navigation and organization.

Search Functionality: Quickly find relevant notes using the powerful search functionality. Search by title or content, enabling users to retrieve specific notes with ease.

##Components in the Notes Application:

**Entities**

Entities represent the data objects stored in the database. In this application, we have two entities:
Category: Represents a category for organizing notes. It contains fields such as id and name.
Note: Represents a note containing title, content, and associated categories.

**Controllers**

Controllers handle HTTP requests and define the endpoints of the RESTful API. In this application, we have two controllers:
CategoryController: Manages operations related to categories, including creating, retrieving, updating, and deleting categories.
NoteController: Manages operations related to notes, including creating, retrieving, updating, and deleting notes.

**DTOs (Data Transfer Objects)**

DTOs are objects used to transfer data between the client and server layers. In this application, we have two DTOs:
CategoryDTO: Represents a category in a transferable format, containing fields like id and name.
NoteDTO: Represents a note in a transferable format, containing fields like id, title, content, and categories.

**Mappers**

Mappers are responsible for converting entities to DTOs and vice versa. In this application, we have two mappers:
CategoryMapper: Converts between Category entities and CategoryDTOs.
NoteMapper: Converts between Note entities and NoteDTOs.

**Unit Tests**
Unit tests ensure that individual components of the application work as expected. In this application, unit tests are written for controllers, services, and mappers to verify their functionality and ensure that they handle various scenarios correctly, such as valid and invalid input, error handling, etc.

##Technologies Used:

Spring Boot: Framework for building Java-based applications.
Spring Data JPA: Simplifies database access and manipulation.
Spring Web: Provides support for building web applications.
H2 Database: Lightweight in-memory database for development and testing.

Use tools like Postman or cURL to interact with the API endpoints.


##Future modifications: 

User Authorization & Authentification, Interface will be added to the application. 


