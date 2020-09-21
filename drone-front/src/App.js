import React from 'react';
import './App.css';
import DroneForm from './js/app/components/DroneForm'
import DronesList from './js/app/components/DronesList'
import {DroneContextProvider} from './js/app/store'

export default ()=>{
  return(
    <DroneContextProvider>
      <DroneForm/>
      <DronesList/>
    </DroneContextProvider>  
  )
}