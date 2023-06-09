<script>
import axios from 'axios';
export default {
    name: 'PizzaForm',
    components: {
    },
    data() {
        return {
            url: 'http://127.0.0.1:8080/api/v1/pizze/create',
            pizza: {
                name: '',
                description: '',
                price: '',
                imageUrl: ''
            }
        }
    },
    methods: {
        createPizza() {
            axios.post(this.url, this.pizza)
                .then(res => {
                    this.$router.push('/');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
    },
}
</script>

<template>
    <main class="container">
        <h1>Crea pizza</h1>
        <form @submit.prevent="createPizza">
            <class class="row gap-2">
                <div class="col-12">
                    <label class="me-3" for="name">Nome</label>
                    <input type="text" name="name" v-model="pizza.name">
                </div>
                <div class="col-12">
                    <label class="me-3" for="description">Descrizione</label>
                    <input type="text" name="description" v-model="pizza.description">
                </div>
                <div class="col-12">
                    <label class="me-3" for="imageUrl">Link immagine</label>
                    <input type="text" name="imageUrl" v-model="pizza.imageUrl">
                </div>
                <div class="col-12">
                    <label class="me-3" for="price">Prezzo</label>
                    <input type="number" name="price" v-model="pizza.price">
                </div>
                <button class="btn btn-primary mt-3" type="submit">Crea</button>
            </class>
        </form>
    </main>
</template>

<style scoped>
h1 {
    text-align: center;
}

button {
    margin-left: 1rem;
}
</style>
