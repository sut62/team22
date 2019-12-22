<template>
  <v-container class="mt-12 ml-12">

   
   <v-layout text-left wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-4">Welcome to Payment</h1>
      </v-flex>
    </v-layout>

   <v-card
      class="mx-500"
      max-width="600"
      outlined
   >
    
      <v-row justify="center">
        <v-col cols="8">
          <v-form v-model="valid" ref="form">
            <v-combobox
              label="เลือกออเดอร์"
              v-model="payment.order"
              :items="order"
              prepend-icon="mdi-cart"
            >
            </v-combobox>

            <v-container fluid>
              <v-radio-group v-model="row" row>
               <v-radio label="เป็นสมาชิก" color="success" value="radio-1"></v-radio>
               <v-radio label="ไม่เป็นสมาชิก" color="error" value="radio-2"></v-radio>
             </v-radio-group>
            </v-container>

             <v-btn color=success
              @click="orderchk"
             >ตกลง</v-btn>

            <v-text-field
              v-model="payment.customer"      
              label="เบอร์โทรศัพท์" 
              prepend-icon="mdi-phone"
            ></v-text-field>

            

		<v-btn color=info
      @click="memberchk"
     >ค้นหา ID</v-btn>

    <v-combobox
      label="เลือกประเภทสมาชิก"
      v-model="payment.membertype"
      :items="order"
      prepend-icon="mdi-human-male"
    >
    </v-combobox>

    <v-combobox
      label="เลือกพนักงานตำแหน่งเคาน์เตอร์" 
      v-model="payment.member"
      :items="order"
      prepend-icon="mdi-human-male"
    >
    </v-combobox>

    <v-btn color=red
      @click="savechk"
    >บันทึกข้อมูล</v-btn>

     </v-form>
      </v-col>
    </v-row>
    
  </v-card>
  </v-container>
</template>

<script>
import format from 'date-fns/format'
import parselISO from 'date-fns/parseISO'
export default {
  name: 'HelloWorld',

  data: () => ({
        menu1:false,
        date:null,
        payment:{
          order:'',
          memver:'',
          customer:'',
          membertype:'',
          row: null,
        },
        service:null,
        
  }),
  computed:{
    formattedDate(){
      return this.date ? format(parselISO(this.date), 'do MMM YYY') : ''
    }
  },
  methods:{
    
    
  }
   
}
</script>
