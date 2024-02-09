import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ExcelService } from './excel.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  constructor(private excelService : ExcelService){}

getTemplate() {
  console.log("Downloading template file")
  this.excelService.generateExcel();
}

  title = 'tca-front';
}

