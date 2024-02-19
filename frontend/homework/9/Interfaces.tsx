export interface DataInterface{
    name:string,
    fullName:string,
    qualification:string,
    skills:SkillInterface[],
    hobbies:HobbiesInterface[]
}
export interface SkillInterface{
    id:number,
    skill:string
}
export interface HobbiesInterface{
    id:number,
    hobby:string
}