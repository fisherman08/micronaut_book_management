import { Writer } from "../writer/Writer";

const BookType = Symbol()

export class Book {
    [BookType]: any

    id: string
    title: string
    authors: Writer[]

    constructor(id: string, title: string, authors: Writer[]) {
        this.id = id
        this.title = title
        this.authors = authors
    }
}
