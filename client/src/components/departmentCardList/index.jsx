import React, {useState, useEffect, useCallback} from "react"
import './index.css'
import axios from 'axios';
import {DepartmentCard} from '../departmentCard';
import {LoadingSpinner} from "../loadingSpinner";

export const DepartmentCardList = () => {
    const [departments, setDepartments] = useState([]);
    const [isLoading, setIsLoading] = useState(false);

    const fetchData = useCallback(() => {
        setIsLoading(true);
        axios.get('http://localhost:8080/department')
            .then(response => {
                setDepartments(response.data);
                setIsLoading(false);
            })
            .catch(error => {
                setIsLoading(true);
                throw new Error(error.message);
        });
    }, [])

    useEffect(() => {
        fetchData();
    }, [fetchData]);

    const updateLectorName = (lectorId, newName) => {
        setIsLoading(true);
        axios.patch(`http://localhost:8080/lector/${lectorId}/change-name?newName=${newName}`)
            .then(_ => {
                fetchData();
            })
            .catch(error => {
                setIsLoading(false);
                throw new Error('Error changing lector name:' + error.message);
            });
    };

    return (
        <div className="department-container">
            {isLoading && <LoadingSpinner />}
            {departments.map(department => (
                <DepartmentCard
                    key={department.id}
                    department={department}
                    updateLectorName={updateLectorName} />
            ))}
        </div>
    )
}