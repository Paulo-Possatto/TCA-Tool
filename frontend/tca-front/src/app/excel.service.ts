import { Injectable } from '@angular/core';
import { Workbook } from 'exceljs';
import * as fs from 'file-saver';

@Injectable({
  providedIn: 'root'
})
export class ExcelService {

  constructor() {

  }

  async generateExcel() {

    const title = 'TCA-Tool Excel File Template';
    const header = ['Transformer ID', 'Sample Date', 'H2', 'O2', 'N2', 'CO', 'CH4', 'CO2', 'C2H4', 'C2H6', 'C2H2', 'Oil Temperature'];

    // Create workbook and worksheet
    const workbook = new Workbook();
    const worksheet = workbook.addWorksheet('Analysis Data');


// Add Row and formatting
    const titleRow = worksheet.addRow([title]);
    titleRow.font = { name: 'Corbel', family: 4, size: 16, underline: 'double', bold: true };
    worksheet.addRow([]);
    const subTitleRow = worksheet.addRow(['This is an generated template for transformer data analysis']);
    worksheet.mergeCells('A1:D2');


// Blank Row
    worksheet.addRow([]);

// Add Header Row
    const headerRow = worksheet.addRow(header);

// Cell Style : Fill and Border
    headerRow.eachCell((cell, number) => {
  cell.fill = {
    type: 'pattern',
    pattern: 'solid',
    fgColor: { argb: 'FFFFFF00' },
    bgColor: { argb: 'FF0000FF' }
  };
  cell.border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } };
});

    worksheet.getColumn(1).width = 15;
    worksheet.getColumn(2).width = 13;
    worksheet.getColumn(12).width = 16;
    worksheet.addRow([]);

// Generate Excel File with given name
    workbook.xlsx.writeBuffer().then((data: any) => {
  const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
  fs.saveAs(blob, 'TCA-Tool_Template.xlsx');
});

  }
}