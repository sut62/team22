<template>
  <div class="mainpage">
    <div class="outside">
      <br>
      <v-col cols = 12>
        <v-container>
          <v-layout row wrap>
            <v-card class="mx-auto"
            outlined>
            <v-col cols = 12>
              <div class="text-center">
                <p class="font-weight-bold">จัดการเมนูอาหาร</p>
              </div>
              <v-text-field
              v-model="ManageMenu.MENU_NAME"
              label="เมนูอาหาร"
              :rules="[(v) => !!v || 'Item is required']"
              hint="ชื่อเมนูอาหารไม่เกิน 30 ตัวอักษร"
              required
              outlined
              dense
              ></v-text-field>

              <v-text-field
              v-model="ManageMenu.MENU_PRICE"
              :rules="[(v) => !!v || 'Item is required']"
              hint="ราคาอาหารไม่เกิน 4 หลัก"
              label="ราคาอาหาร"
              required
              outlined
              dense
              suffix="฿"
              ></v-text-field>
              <br>
              <v-select
              v-model="ManageMenu.cid"
              :items="MenuCate"
              label="ประเภทอาหาร"
              item-text="cname"
              item-value="cid"
              outlined
              dense
              ></v-select>

              <v-select
              v-model="ManageMenu.id"
              :items="MenuIngre"
              label="ส่วนประกอบหลัก"
              item-text="iname"
              item-value="id"
              outlined
              dense
              ></v-select>

              <v-select
              v-model="ManageMenu.tid"
              :items="MenuType"
              label="การปรุงอาหาร"
              item-text="tname"
              item-value="tid"
              outlined
              dense
              ></v-select>

              <div class="text-center">
                <v-btn
                small
                @click="saveData"
                tile
                color="success"
                >Add Menu</v-btn>
                <br>
              </div>

            </v-col>
          </v-card>
          <v-card
          class="mx-auto"
          max-width="1000"
          outlined>
          <v-col cols = 12>

            <v-simple-table
            fixed-header>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">ชื่อเมนู</th>
                  <th class="text-left">ประเภทอาหาร</th>
                  <th class="text-left">ส่วนประกอบหลัก</th>
                  <th class="text-left">การปรุงอาหาร</th>
                  <th class="text-left">ราคา</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="item in showtable" :key="item.name">
                  <td class="text-left">{{ item.m_name }}</td>
                  <td class="text-left">{{ item.sel_cate.cname }}</td>
                  <td class="text-left">{{ item.sel_ingre.iname }}</td>
                  <td class="text-left">{{ item.sel_type.tname }}</td>
                  <td class="text-left">{{ item.m_price+" ฿" }}</td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>

        </v-col>
      </v-card>
    </v-layout>
  </v-container>
</v-col>

</div>
</div>
</template>

<script>
import http from "../plugins/https"
export default {
  name:"ManageMenu",
  data(){
    return{
      ManageMenu:{
        cid:"",
        id:"",
        tid:"",
      },
      MENU_NAME:"",
      MENU_PRICE:"",
      MenuCate:[],
      MenuIngre:[],
      MenuType:[],
      showtable:[]
    };
  },
  methods:{
    saveData(){
      http.post("/manageMenus/"+
      this.ManageMenu.MENU_NAME
      +"/"+
      this.ManageMenu.MENU_PRICE
      +"/"+
      this.ManageMenu.cid
      +"/"+
      this.ManageMenu.id
      +"/"+
      this.ManageMenu.tid)
      .then(response => {
        console.log(response);
        alert("เพิ่มเมนูสำเร็จ")
        location.reload();
        this.getDataTable();

      })
      .catch(e => {
        console.log(e);
        alert("เพิ่มเมนูไม่สำเร็จ")
      })
      ;
      this.submitted = true;
    },

    getMenuCate(){

      http.get("/menuCates").then(response => {
        this.MenuCate = response.data;
      });
      this.submitted = true;
    },
    getMenuIngre(){

      http.get("/menuIngres").then(response =>{
        this.MenuIngre = response.data;
      });
      this.submitted = true;
    },
    getMenuType(){
      http.get("/menuTypes").then(response =>{
        this.MenuType = response.data;
      });
      this.submitted = true;

    },
    getDataTable(){
      http.get("/manageMenus").then(response =>{
        this.showtable = response.data;
      });
      this.submitted = true;
    }
  },
  mounted(){
    this.getMenuCate();
    this.getMenuIngre();
    this.getMenuType();
    this.getDataTable();
  }
}
</script>
