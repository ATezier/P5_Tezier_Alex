# SafetyNet API

The SafetyNet API is a comprehensive system designed to manage and manipulate critical information related to individuals, fire stations, and medical records. This API exposes various endpoints to facilitate the seamless integration of data and the execution of essential operations.

## Endpoints

### /person

This endpoint serves as the interface for CRUD operations related to individual records:

- **POST/PUT/DELETE:** Add a new person, update existing details (assuming static first name and last name), or delete a person using a unique identifier (combination of first name and last name).

Example URL: `http://localhost:8080/person`

### /firestation

This endpoint manages mappings between fire stations and addresses:

- **POST/PUT/DELETE:** Add a mapping, update the fire station number for an address, or delete the mapping of a fire station or an address.

Example URL: `http://localhost:8080/firestation`

### /medicalRecord

This endpoint handles medical record-related operations:

- **POST/PUT/DELETE:** Add a medical record, update an existing record (assuming a static first name and last name), or delete a medical record using a unique identifier (combination of first name and last name).

Example URL: `http://localhost:8080/medicalRecord`

## Dynamic URLs Provided by the API

### /firestation?stationNumber=<station_number>

This endpoint dynamically returns a list of individuals covered by a specific fire station, including detailed information such as first name, last name, address, phone number, and counts of adults and children.

Example URL: `http://localhost:8080/firestation?stationNumber=1`

### /childAlert?address=<address>

This endpoint generates a list of children (aged 18 or younger) residing at a given address, along with their names, ages, and details of other household members.

Example URL: `http://localhost:8080/childAlert?address=1509 Culver St`

### /phoneAlert?firestation=<firestation_number>

For emergency purposes, this endpoint provides a list of phone numbers for residents served by a specific fire station.

Example URL: `http://localhost:8080/phoneAlert?firestation=1`

### /fire?address=<address>

Returns a comprehensive list of residents at a specified address, including their names, phone numbers, ages, and medical histories, along with the corresponding fire station number.

Example URL: `http://localhost:8080/fire?address=1509 Culver St`

### /flood/stations?stations=<a list of station_numbers>

Generates a detailed list of households served by specified fire stations, grouped by address. Includes resident names, phone numbers, ages, and medical histories.

Example URL: `http://localhost:8080/flood/stations?stations=1,2,3`

### /personInfo?firstName=<firstName>&lastName=<lastName>

Provides detailed information about residents, including name, address, age, email address, and medical history, based on first and last names. If there are multiple individuals with the same name, all will be included.

Example URL: `http://localhost:8080/personInfo?firstName=John&lastName=Boyd`

### /communityEmail?city=<city>

Offers email addresses for all residents in a specified city.

Example URL: `http://localhost:8080/communityEmail?city=Culver`

## Installation

SafetyNet API is built with Java 11 and Spring Boot 2.6.4, ensuring a robust and efficient execution environment. Follow these steps to set up the project:

1. Clone the repository: `git clone https://github.com/your-username/safetynet-api.git`
2. Navigate to the project directory: `cd safetynet-api`
3. Ensure you have Java 11 installed on your system.
4. Build the project using Maven: `./mvnw clean install`
5. Run the application: `./mvnw spring-boot:run`

## Usage

Example request with URL:

```bash
curl -X GET http://localhost:8080/firestation?stationNumber=1