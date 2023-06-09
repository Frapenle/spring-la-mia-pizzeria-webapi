<script>
import PizzaComponent from '../components/PizzaComponent.vue';
export default {
    components: {
        PizzaComponent
    }
}
</script>

<template>
    <div class="mt-3">
        <router-link to="/create">Crea pizza</router-link>
    </div>
    <div>
        <PizzaComponent />
    </div>
</template>