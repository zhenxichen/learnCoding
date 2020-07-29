<template>
  <div class="hello">
    <button v-on:click="onclick">Post Test</button>
    <button v-on:click="helloclick">Hello</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      
    }
  },
  methods: {
    onclick: function() {
      this.$axios({
        method: "post",
        url: "http://49.232.26.148:6567/announce_list",
        headers: {
          "Content-Type": 'application/json',
          "Authorization": 'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QiLCJyb2xlIjoiYWRtaW4iLCJleHAiOjE1OTU4OTM4MzZ9.MJv9z_jhXZACB-PaCLGEZVLa-bB1WXobou42lqG6_Hs'
        },
        transformRequest: [function(data) {
          data = JSON.stringify(data)
          return data
        }],
        params: {},
      })
      .then(res => {
        console.log(res);
      })
      .catch(err => {
        console.log(err);
      })
    },
    helloclick: function() {
      this.$axios({
        method: "post",
        url:"http://127.0.0.1:8080/login",
        headers: {
          'Content-Type': 'application/json',
        },
        transformRequest: [function(data) {
          data = JSON.stringify(data)
          return data
        }],
        params: {},
        data: {
          "username": "test",
          "password": "test"
        }
      })
      .then((res) => {
        console.log(res);
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

button {
  width: 50px;
  height: 50px;
}

</style>
