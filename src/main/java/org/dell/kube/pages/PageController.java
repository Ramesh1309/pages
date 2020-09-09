package org.dell.kube.pages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/pages")
public class PageController {
    Logger logger =(Logger)LoggerFactory.getLogger(this.getClass());
    private IPageRepository pageRepository;
    public PageController(IPageRepository pageRepository)
    {
        this.pageRepository = pageRepository;
    }
    @PostMapping
    public ResponseEntity<Page> create(@RequestBody Page page) {
        Long id = page.getId();
        logger.info("Going to Create a Page with ID = " +id );
        logger.debug(" Going towards pageRepo id = " + id);
        Page newPage= pageRepository.create(page);
        logger.debug(" Page ID = " + id +", inserted successfully!!");
        return new ResponseEntity<Page>(newPage, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<Page> read(@PathVariable long id) {
        logger.info("READ-INFO:Fetching page with id = " + id);
        logger.debug("READ-DEBUG:Fetching page with id = " + id);
        Page page = pageRepository.read(id);
        if(page!=null)
            return new ResponseEntity<Page>(page, HttpStatus.OK);
        else
            logger.error("READ-ERROR:Could not find page with id = " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<Page>> list() {
        logger.info("Before PageRepo List Method ");
        List<Page> pages= pageRepository.list();
        logger.info("PageRepo List Method retrieved Successfully");
        return new ResponseEntity<List<Page>>(pages, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<Page> update(@RequestBody Page page, @PathVariable long id) {
        logger.info("update-INFO:Fetching page with id = " + id);
        logger.debug("update-DEBUG:Fetching page with id = " + id);
        Page updatedPage= pageRepository.update(page,id);
        if(updatedPage!=null)
            return new ResponseEntity<Page>(updatedPage, HttpStatus.OK);
        else
            logger.error("update-ERROR:Could not find page with id = " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable long id) {
        logger.info("Delete-INFO:Fetching page with id = " + id);
        logger.debug("Delete-DEBUG:Fetching page with id = " + id);
        pageRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
