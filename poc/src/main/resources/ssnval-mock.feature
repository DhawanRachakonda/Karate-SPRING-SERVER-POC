 Feature: PetMock Mock

 Background:
 * def people = [{ "id": "abc", "name": "Dhawan", "area": "Nallakunta", "city": "Hyderabad"} , { id: "def", "name": "Manga", "area": "Nallakunta", "city": "Hyderabad" }, { id: "ghi", "name": "Srinivas", "area": "Nallakunta", "city": "Hyderabad" } ]

 @findPetsByStatus
 Scenario:  methodIs('post') && pathMatches('/people/add')
  * def people_req = request
  * def peoples = people_req.people
  * people.forEach(peopleRec => { index = peoples.findIndex(person => person.name == peopleRec.name); if (index >= 0) { peoples[index].id = peopleRec.id }})
  * def response = people
  * def responseStatus = 200