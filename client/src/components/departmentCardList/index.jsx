import React, { useState } from "react"
import './index.css'
import {DepartmentCard} from '../departmentCard';

export const DepartmentCardList = () => {
    const [departments, setDepartments] = useState([
        {
            "id": "65b20b5a57bbacb3bffb2eec",
            "name": "Computer Science",
            "lectors": [
                {
                    "id": "65b20b5a57bbacb3bffb2ee9",
                    "name": "Olga Tokar",
                    "degree": "PROFESSOR"
                },
                {
                    "id": "65b20b5a57bbacb3bffb2eea",
                    "name": "Sergii Prokopenko",
                    "degree": "ASSOCIATE_PROFESSOR"
                }
            ]
        },
        {
            "id": "65b20b5a57bbacb3bffb2eed",
            "name": "Mathematics",
            "lectors": [
                {
                    "id": "65b20b5a57bbacb3bffb2eeb",
                    "name": "Svitlana Kolisnechenko",
                    "degree": "ASSISTANT"
                }
            ]
        },
        {
            "id": "65b20b5a57bbacb3bffb2eed",
            "name": "Phsy",
            "lectors": [
                {
                    "id": "65b20b5a57bbacb3bffb2eeb",
                    "name": "Svitlana Kolisnechenko",
                    "degree": "ASSISTANT"
                },
                {
                    "id": "65b20b5a57bbacb3bffb2eea",
                    "name": "Sergii Prokopenko",
                    "degree": "ASSOCIATE_PROFESSOR"
                }
            ]
        }
    ]);

    const updateLectorName = (lecturerId, newName) => {
        const newDepartments = departments.map(department => {
            const newLectors = department.lectors.map(lecturer => {
                if (lecturer.id === lecturerId) {
                    return { ...lecturer, name: newName };
                }
                return lecturer;
            });
            return { ...department, lectors: newLectors };
        });
        setDepartments(newDepartments);
    };

    return (
        <div className="department-container">
            {departments.map(department => (
                <DepartmentCard
                    key={department.id}
                    department={department}
                    updateLectorName={updateLectorName} />
            ))}
        </div>
    )
}