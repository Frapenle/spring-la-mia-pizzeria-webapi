<script>
import axios from 'axios';
export default {
    name: 'PizzaComponent',
    data() {
        return {
            url: 'http://127.0.0.1:8080/api/v1/pizze/',
            pizze: [],
            name: '',
            deleted: false
        }
    },
    methods: {
        getPizze() {
            axios.get(this.url + 'search', {
                params: {
                    name: this.name
                }
            })
                .then((response) => {
                    this.pizze = response.data;
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        deletePizza(id) {
            axios.delete(this.url + id)
                .then((response) => {
                    this.deleted = true
                })
                .catch((error) => {
                    console.log(error)
                })
        }
    },
    mounted() {
        this.getPizze();
    },
}
</script>

<template>
    <h1>Pizze</h1>
    <main>
        <div>
            <input type="text" v-model="name" @keyup="getPizze" placeholder="Cerca" autocomplete="off">
        </div>
        <ul>
            <li v-for="pizza in pizze">
                {{ pizza.name }}
                <button @click="deletePizza(pizza.id)" :key="pizza.id">Elimina</button>
            </li>
        </ul>
    </main>
</template>