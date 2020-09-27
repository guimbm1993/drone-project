import React, {useContext, useState} from 'react'
import {DroneContext} from '../store'
import { Col, Form, FormGroup, Label, Input, CustomInput, Button } from 'reactstrap'

export default()=>{
    const idDrone = useFormInput("")
    const latitude = useFormInput("")
    const longitude = useFormInput("")
    const temperaturaAr = useFormInput("")
    const umidadeAr = useFormInput("")
    const ativarRastreamento = true
    // eslint-disable-next-line no-unused-vars
    const [state,dispatch] = useContext(DroneContext)

    const droneData = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ idDrone: idDrone.value, latitude: latitude.value, longitude: longitude.value
        , temperaturaAr: temperaturaAr.value, umidadeAr: umidadeAr.value })
    };

    const onSubmit = event =>{
        fetch('http://127.0.0.1:8080/drones', droneData)
        .then(response => response.json());
        event.preventDefault()
        dispatch({
            type: "ADD_DRONE",
            payload:{idDrone: idDrone.value, latitude: latitude.value, longitude:longitude.value, 
                temperaturaAr: temperaturaAr.value, umidadeAr: umidadeAr.value, ativarRastreamento:ativarRastreamento.value}
        })
    }

    return (
        <Col md="4">
            <h3>Adicionar rastreamento</h3>
            <Form onSubmit={onSubmit}>
                <FormGroup>
                    <Label>ID Drone:</Label>
                    <Input {...idDrone} type="text" name="idDrone" placeholder="Id Drone" required autoFocus/>
                </FormGroup>
                <FormGroup>
                    <Label>Latitude:</Label>
                    <Input {...latitude} type="text" name="latitude" placeholder="Latitude" required />
                </FormGroup>
                <FormGroup>
                    <Label>Longitude:</Label>
                    <Input {...longitude} type="text" name="longitude" placeholder="Longitude" required />
                </FormGroup>
                <FormGroup>
                    <Label>Temperatura do Ar:</Label>
                    <Input {...temperaturaAr} type="text" name="temperaturaAr" placeholder="Temperatura do ar" required />
                </FormGroup>
                <FormGroup>
                    <Label>Umidade do Ar:</Label>
                    <Input {...umidadeAr} type="text" name="umidadeAr" placeholder="Umidade ar" required />
                </FormGroup>
                <FormGroup>
                    <Label>Ativar rastreamento?</Label>
                    <CustomInput type="radio" name="optionRastreamento" value="sim" label="Sim" />
                    <CustomInput type="radio" name="optionRastreamento" value="nao" label="Não" />
                </FormGroup>
                <Button color="primary">Enviar</Button>
            </Form>
        </Col>
        
    )

}



function useFormInput(initialValue){
    const [value, setValue] = useState(initialValue)
    const handleChange = e =>{
        setValue(e.target.value)
    }
    return{
        value,
        onChange: handleChange
    }
}