import React, {useContext, useState} from 'react'
import {DroneContext} from '../store'
import { Col, Form, FormGroup, Label, Input, CustomInput, Button } from 'reactstrap'

export default()=>{
    const idDrone = useFormInput("")
    const latitude = useFormInput("")
    const longitude = useFormInput("")
    const temperaturaAr = useFormInput("")
    const umidadeAr = useFormInput("")
    const ativarRastreamento = useFormInput("")
    // eslint-disable-next-line no-unused-vars
    const [state,dispatch] = useContext(DroneContext)

    const droneData = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ idDrone: idDrone.value, latitude: latitude.value, longitude: longitude.value
        , temperaturaAr: temperaturaAr.value, umidadeAr: umidadeAr.value })
    };

    const onSubmit = event =>{

        if(document.getElementById('sim').checked){
            ativarRastreamento.value = true
        }else{
            ativarRastreamento.value = false
        }

        event.preventDefault()
        dispatch({
            type: "ADD_DRONE",
            payload:{idDrone: idDrone.value, latitude: latitude.value, longitude:longitude.value, 
                temperaturaAr: temperaturaAr.value, umidadeAr: umidadeAr.value, ativarRastreamento:ativarRastreamento.value}
        })
        fetch('http://127.0.0.1:8080/drones', droneData)
        .then(response => response.json());
    }

   const clickedYes = ()=>{
        ativarRastreamento.value = true
    }

    const clickedNo = (f)=>{
        ativarRastreamento.value = false
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
                    <Input {...latitude} type="number" name="latitude"  placeholder="Latitude" required />
                </FormGroup>
                <FormGroup>
                    <Label>Longitude:</Label>
                    <Input {...longitude} type="number" name="longitude"  placeholder="Longitude" required />
                </FormGroup>
                <FormGroup>
                    <Label>Temperatura do Ar:</Label>
                    <Input {...temperaturaAr} type="number" name="temperaturaAr" min="-25" max="40" placeholder="Temperatura do ar" required />
                </FormGroup>
                <FormGroup>
                    <Label>Umidade do Ar:</Label>
                    <Input {...umidadeAr} type="number" name="umidadeAr" min="0" max="100" placeholder="Umidade ar" required />
                </FormGroup>
                <FormGroup>
                    <Label>Ativar rastreamento</Label>
                    <br></br>
                    <input type="radio" id="sim" name="optionStar" value="true" onClick={clickedYes}/>
                    <label>SIM</label><br></br>
                    <input type="radio" id="nao" name="optionStar" value="false" onClick={clickedNo}/>
                    <label>NÃO</label>
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