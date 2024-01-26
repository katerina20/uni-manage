db = db.getSiblingDB('university');

var lector1Id = ObjectId();
var lector2Id = ObjectId();
var lector3Id = ObjectId();

db.lectors.insertMany([
    { _id: lector1Id, name: "Olga Tokar", degree: "PROFESSOR", departments: [] },
    { _id: lector2Id, name: "Sergii Prokopenko", degree: "ASSOCIATE_PROFESSOR", departments: [] },
    { _id: lector3Id, name: "Svitlana Kolisnechenko", degree: "ASSISTANT", departments: [] }
]);

var dept1Id = ObjectId();
var dept2Id = ObjectId();

db.departments.insertMany([
    { _id: dept1Id, name: "Computer Science", lectors: [] },
    { _id: dept2Id, name: "Mathematics", lectors: [] }
]);

db.departments.updateOne(
    { _id: dept1Id },
    { $addToSet: { lectors: { $each: [lector1Id, lector2Id] } } }
);

db.departments.updateOne(
    { _id: dept2Id },
    { $addToSet: { lectors: lector3Id } }
);

// Linking departments to lectors
db.lectors.updateOne(
    { _id: lector1Id },
    { $addToSet: { departments: dept1Id } }
);

db.lectors.updateOne(
    { _id: lector2Id },
    { $addToSet: { departments: { $each: [dept1Id, dept2Id] } } }
);

db.lectors.updateOne(
    { _id: lector3Id },
    { $addToSet: { departments: dept2Id } }
);