import React from 'react';
import './App.css';
import {Container, Row, Col} from 'reactstrap'
import DroneForm from './js/app/components/DroneForm'
import DronesList from './js/app/components/DronesList'
import {DroneContextProvider} from './js/app/store'

export default ()=>{
  return(
    <DroneContextProvider>
      <Container>
            <Row className="justify-content-center">
                <Col md="12">
                  <h1>Rastreamento de Drones</h1>
                </Col>
                <DroneForm/>
                <DronesList/>
            </Row>
      </Container>
    </DroneContextProvider>  
  )
}