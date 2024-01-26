db = db.getSiblingDB('university');

var lector1Id = ObjectId();
var lector2Id = ObjectId();
var lector3Id = ObjectId();
var lector4Id = ObjectId();
var lector5Id = ObjectId();
var lector6Id = ObjectId();
var lector7Id = ObjectId();
var lector8Id = ObjectId();
var lector9Id = ObjectId();

db.lectors.insertMany([
    { _id: lector1Id, name: "Olga Tokar", degree: "PROFESSOR" },
    { _id: lector2Id, name: "Sergii Prokopenko", degree: "ASSOCIATE_PROFESSOR" },
    { _id: lector3Id, name: "Svitlana Kolisnechenko", degree: "ASSISTANT" },
    { _id: lector4Id, name: "Yaroslav Ivanenko", degree: "ASSISTANT" },
    { _id: lector5Id, name: "Denys Tkachenko", degree: "ASSOCIATE_PROFESSOR" },
    { _id: lector6Id, name: "Yuliya Shevchenko", degree: "ASSOCIATE_PROFESSOR" },
    { _id: lector7Id, name: "Pavlo Mykhailenko", degree: "PROFESSOR" },
    { _id: lector8Id, name: "Oleksandr Zhytomyrsky", degree: "PROFESSOR" },
    { _id: lector9Id, name: "Andrii Kovalchuk", degree: "ASSISTANT" }
]);

var dept1Id = ObjectId();
var dept2Id = ObjectId();
var dept3Id = ObjectId();
var dept4Id = ObjectId();

db.departments.insertMany([
    { _id: dept1Id, name: "Computer Science", lectors: [] },
    { _id: dept2Id, name: "Mathematics", lectors: [] },
    { _id: dept3Id, name: "Chemistry", lectors: [] },
    { _id: dept4Id, name: "History", lectors: [] }
]);

db.departments.updateOne(
    { _id: dept1Id },
    { $addToSet: { lectors: { $each: [lector1Id, lector2Id, lector5Id, dept3Id] } } }
);

db.departments.updateOne(
    { _id: dept2Id },
    { $addToSet: { lectors: { $each: [lector3Id, lector6Id] } } }
);

db.departments.updateOne(
    { _id: dept3Id },
    { $addToSet: { lectors: { $each: [lector7Id, lector8Id, lector9Id, lector4Id, lector6Id, lector2Id, lector1Id] } } }
);

db.departments.updateOne(
    { _id: dept4Id },
    { $addToSet: { lectors: { $each: [lector4Id, lector3Id, lector1Id] } } }
);