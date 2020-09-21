import React, {useContext} from 'react'
import {DroneContext} from '../store'

export default()=>{
    const [state] = useContext(DroneContext);
    const rows = state.drones.map((drone, index) => 
        <tr key={index}>
            <td>{drone.idDrone}</td>
            <td>{drone.latitude}</td>
            <td>{drone.longitude}</td>
            <td>{drone.temperaturaAr}</td>
            <td>{drone.umidadeAr}</td>
            <td><a target="_blank" href="https://www.google.com/maps/search/?api=1&query=47.5951518,-122.3316393">Ver</a></td>
        </tr>
    )
    return (
        <div id="drone-list" className="py-3">
            <table className="table">
                <thead>
                    <tr>
                        <td>Id Drone</td>
                        <td>Latitude</td>
                        <td>Longitude</td>
                        <td>Temperatura Ar</td>
                        <td>Umidade Ar</td>
                        <td>Rastreamento</td>
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        </div>
    )
}