import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ScoreService} from '../services/score.service';
import {IMonthlyIncomeBracket} from '../model/IMonthlyIncomeBracket';
import {ICity} from '../model/ICity';
import {MessageService} from 'primeng/api';
import {User} from '../model/User';
import {UserCalculateRequest} from '../model/UserCalculateRequest';

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrls: ['./score.component.css']
})
export class ScoreComponent implements OnInit {

  tcNo: number;
  nameSurname: string;
  phoneNumber: string;
  form: FormGroup;
  brackets: IMonthlyIncomeBracket[] = [];
  selectedBracket: IMonthlyIncomeBracket;
  cities: ICity[] = [];
  selectedCity: ICity;
  userCalculateRequest: UserCalculateRequest = new UserCalculateRequest();
  showPopup = false;
  popupText: string;

  constructor(private formBuilder: FormBuilder, private scoreService: ScoreService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      tcNo: [null,
        [Validators.required,
        Validators.maxLength(11), Validators.minLength(11)]],
      phoneNumber: [null, [Validators.required]],
      name: [null, [Validators.required] ],
      surname: [null, [Validators.required]],
      monthlyIncomeBracketCode: [null, [Validators.required]],
      cityCode: [null, [Validators.required]]
    });
    this.scoreService.getAllMonthlyIncomeBracket().subscribe(resp => {
      this.brackets = resp;
    });
    this.scoreService.getAllCities().subscribe(resp => {
      this.cities = resp;
    });
  }

  get f() {
    return this.form.controls;
  }

  submit(){
    if (!this.form.valid)
      return;

    this.userCalculateRequest.cityCode = this.form.value.cityCode.code;
    this.userCalculateRequest.monthlyIncomeBracketCode = this.form.value.monthlyIncomeBracketCode.code;
    this.userCalculateRequest.name = this.form.value.name;
    this.userCalculateRequest.surname = this.form.value.surname;
    this.userCalculateRequest.phoneNumber = this.form.value.phoneNumber;
    this.userCalculateRequest.tcNo = this.form.value.tcNo;
    this.scoreService.calculateUserScore(this.userCalculateRequest).subscribe(resp => {
      this.popupText = 'Sayın ' + this.userCalculateRequest.name + ' ' + this.userCalculateRequest.surname + ' toplam skorunuz : ' + resp;
      this.showPopup = true;
    }, ex => {
        this.showError("Bir hata oluştu bilgilerinizi kontrol ediniz!");
      });

  }

  showError(message: string) {
    this.messageService.add({key: 'notificationToast',severity:'error', summary: 'Hata', detail: message});
  }
}
