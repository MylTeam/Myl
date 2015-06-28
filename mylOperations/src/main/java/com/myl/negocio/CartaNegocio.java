package com.myl.negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.CartaDao;
import com.myl.modelo.Carta;

@Service("cartaNegocio")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CartaNegocio {
	private CartaDao cartaDao;

	public List<Carta> getCardsFromExcel(File file) {
		List<Carta> cartas = new ArrayList<Carta>();
		try {
			FileInputStream fis = new FileInputStream(file);

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Carta carta = new Carta();

				carta.setNombre(row.getCell(0).getStringCellValue());
				carta.setEfecto(row.getCell(1).getStringCellValue());
				carta.setLeyenda(row.getCell(2).getStringCellValue());
				carta.setNumero(row.getCell(3).getStringCellValue());
				carta.setTipo(row.getCell(4).getStringCellValue());
				carta.setRaza(row.getCell(5).getStringCellValue());
				carta.setFrecuencia(row.getCell(6).getStringCellValue());
				carta.setCoste((int) row.getCell(7).getNumericCellValue());
				carta.setFuerza((int) row.getCell(8).getNumericCellValue());
				cartas.add(carta);

			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartas;
	}

	public void unzipImages(File zipFile, String outputFolder) {

		byte[] buffer = new byte[1024];

		try {

			// create output directory is not exists
			File folder = new File(outputFolder);
			if (!folder.exists()) {
				folder.mkdir();
			}

			// get the zip file content
			ZipInputStream zis = new ZipInputStream(
					new FileInputStream(zipFile));
			// get the zipped file list entry
			ZipEntry ze = zis.getNextEntry();

			while (ze != null) {

				String fileName = ze.getName();
				File newFile = new File(outputFolder + File.separator
						+ fileName);

				System.out.println("file unzip : " + newFile.getAbsoluteFile());

				// create all non exists folders
				// else you will hit FileNotFoundException for compressed folder
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();
				ze = zis.getNextEntry();
			}

			zis.closeEntry();
			zis.close();

			System.out.println("Done");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@Transactional
	public List<Carta> findAll() {
		return cartaDao.findAll();
	}

	@Transactional
	public Carta findById(Integer id) {
		return cartaDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Carta save(Carta entidad) {
		Carta modelo = cartaDao.save(entidad);
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Carta entidad) {
		cartaDao.delete(entidad);
	}

	@Transactional
	public List<Carta> findByExample(Carta carta) {
		return cartaDao.findByExample(carta);
	}

	@Transactional
	public List<String> findByCriteria() {
		return cartaDao.findByCriteria();
	}

	@Transactional
	public List<String> findByCriteriaTipo() {
		return cartaDao.findByCriteriaTipo();
	}

	@Transactional
	public List<Carta> findByCriterioBusqueda(Carta carta) {
		return cartaDao.findByCriterioBusqueda(carta);
	}

	public CartaDao getCartaDao() {
		return cartaDao;
	}

	public void setCartaDao(CartaDao cartaDao) {
		this.cartaDao = cartaDao;
	}

}
