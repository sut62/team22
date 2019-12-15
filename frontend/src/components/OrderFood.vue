<template>
  <v-container>
    <v-card color="food">
    <v-card-title class="text-center justify-center py-6">
      <h1 class="font-weight-bold display-3 food--text">Food Order</h1>
    </v-card-title>

    <v-tabs
      background-color="transparent"
      color="food"
      grow
    >
      <v-tab>
        <div> MENU </div>
     </v-tab>
    </v-tabs>
  </v-card>

   <v-form v-model="valid" ref="form">
   <v-row align="center">
      <v-col class="d-flex" cols="12" sm="6">
         <v-select
          v-model="e1"
          :items="num"
          label="เลือกโต๊ะ"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกพัสดุ']"
          chips
        ></v-select>
      </v-col>

      <v-col class="d-flex" cols="12" sm="6">
         <v-select
          v-model="e2"
          :items="states"
          label="เลือกเมนูอาหาร"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกพัสดุ']"
          chips
        ></v-select>
      </v-col>

      <v-col class="d-flex" cols="12" sm="6">
        <v-text-field
            label="จำนวน"
            :rules="[(v) => !!v || 'ยังไม่ได้เลือกพัสดุ']"
          ></v-text-field>
      </v-col>

      <v-col class="d-flex" cols="12" sm="6">
        <v-select
          v-model="e4"
          :items="scale"
          label="ขนาด"
          :rules="[(v) => !!v || 'ยังไม่ได้เลือกพัสดุ']"
          chips
        ></v-select>
      </v-col>
    </v-row>
   
      <div class = "text-center" cols="12" sm="4">
        <v-btn class="{ red: !valid, green: valid }">SAVE</v-btn>
      </div>
    </v-form>

    <div>
    <v-data-table
      :headers="headers"
      :items="items"
      :items-per-page="5"
      :options.sync="options"
      :loading="loading"
      class="elevation-1 mt-9 food"
    ></v-data-table>
  </div>
  </v-container>
</template>

<script>
import http from '../plugins/https';
  export default {
    data () {
      return {
         e1: [],
         e2: [],
         e3: [],
         e4: [],
        loading: true,
        states: [
          'ข้าวไข่เจียว','ข้าวกะเพราหมู'
        ],
        num: [
          '1','2','3','4'
        ],
        scale: [
          'เล็ก','กลาง','ใหญ่'
        ],
        headers: [
          {
            text: 'TABLE',
            align: 'left',
            sortable: false,
            value: 'name',
          },
          { text: 'MENU', value: 'MENU' },
          { text: 'DishSize', value: 'DishSize' },
          { text: 'DishNumber', value: 'DishNumber' }
        ],
      }
    },
    methods: {
    }
  }
</script>
<style>
/* Helper classes */
.food {
  background-color: #FFFBE6 !important;
}
.food--text {
  color: #356859 !important;
}
</style>
