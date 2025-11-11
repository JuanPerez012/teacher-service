package com.teachermicroservice.service.impl;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.service.TeacherExcelImportService;
import com.teachermicroservice.service.TeacherService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TeacherExcelImportServiceImpl implements TeacherExcelImportService {

    private final TeacherService teacherService;

    public TeacherExcelImportServiceImpl(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public List<TeacherResponseDTO> importFromExcel(MultipartFile file) throws IOException {
        List<TeacherResponseDTO> created = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            if (!rows.hasNext()) return created;

            // leer encabezado
            Row header = rows.next();
            List<String> headers = new ArrayList<>();
            for (Cell c : header) {
                headers.add(getCellValueAsString(c).trim().toLowerCase());
            }

            DateTimeFormatter[] dateFormats = new DateTimeFormatter[] {
                    DateTimeFormatter.ISO_LOCAL_DATE,
                    DateTimeFormatter.ofPattern("d/MM/yyyy"),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
            };

            while (rows.hasNext()) {
                Row row = rows.next();
                if (isRowEmpty(row)) continue;

                String firstName = null;
                String lastName = null;
                String email = null;
                LocalDate birthDate = null;
                String gender = null;
                String phone = null;
                String address = null;
                String specialty = null;
                LocalDate hireDate = null;
                BigDecimal salary = null;
                String academicRank = null;
                String department = null;

                for (int i = 0; i < headers.size(); i++) {
                    String key = headers.get(i);
                    Cell cell = row.getCell(i);
                    String value = getCellValueAsString(cell).trim();
                    if (value.isEmpty()) continue;

                    switch (key) {
                        case "first_name":
                        case "firstname":
                        case "first name":
                        case "name":
                            firstName = value;
                            break;
                        case "last_name":
                        case "lastname":
                        case "last name":
                            lastName = value;
                            break;
                        case "email":
                            email = value;
                            break;
                        case "document":
                        case "document_number":
                        case "dni":
                            break;
                        case "gender":
                            String g = value.toUpperCase();
                            if (g.contains("M")) gender = "MASCULINO";
                            else if (g.contains("F")) gender = "FEMENINO";
                            else gender = "OTRO";
                            break;
                        case "birthdate":
                        case "birth_date":
                        case "date_of_birth":
                        case "birth date":
                            birthDate = parseDate(value, dateFormats);
                            break;
                        case "phone":
                        case "telefono":
                        case "tel":
                            phone = value;
                            break;
                        case "address":
                        case "direccion":
                            address = value;
                            break;
                        case "specialty":
                        case "especialidad":
                            specialty = value;
                            break;
                        case "hiredate":
                        case "hire_date":
                        case "fecha_contratacion":
                        case "hire date":
                            hireDate = parseDate(value, dateFormats);
                            break;
                        case "salary":
                        case "sueldo":
                        case "salario":
                            try {
                                salary = new BigDecimal(value.replaceAll("[^0-9.,-]", "").replace(',', '.'));
                            } catch (Exception ignored) {}
                            break;
                        case "academic_rank":
                        case "academicrank":
                            academicRank = value;
                            break;
                        case "department":
                        case "departamento":
                            department = value;
                            break;
                        default:

                            break;
                    }
                }

                TeacherRequestDTO dto = new TeacherRequestDTO(
                        firstName,
                        lastName,
                        email,
                        birthDate,
                        gender,
                        phone,
                        address,
                        specialty,
                        hireDate,
                        salary,
                        academicRank,
                        department
                );

                TeacherResponseDTO createdDto = teacherService.createTeacher(dto);
                created.add(createdDto);
            }

        }

        return created;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case BOOLEAN -> Boolean.toString(cell.getBooleanCellValue());
            case NUMERIC -> {
                if (DateUtil.isCellDateFormatted(cell)) {
                    yield cell.getLocalDateTimeCellValue().toLocalDate().toString();
                } else {
                    double d = cell.getNumericCellValue();
                    long lv = (long) d;
                    if (lv == d) yield Long.toString(lv);
                    yield Double.toString(d);
                }
            }
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            default -> "";
        };
    }

    private boolean isRowEmpty(Row row) {
        if (row == null) return true;
        for (Cell c : row) {
            if (c != null && c.getCellType() != CellType.BLANK && !getCellValueAsString(c).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private LocalDate parseDate(String value, DateTimeFormatter[] formats) {
        for (DateTimeFormatter fmt : formats) {
            try {
                return LocalDate.parse(value, fmt);
            } catch (Exception ignored) {
            }
        }
        try {
            return LocalDate.parse(value);
        } catch (Exception ignored) {
        }
        return null;
    }
}
