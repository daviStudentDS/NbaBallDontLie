using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;

namespace BallDo.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TeamController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public TeamController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet]
        public IActionResult GetAllTeams()
        {
            var teams = _context.Teams
                .Select(t => new
                {
                    t.Id,
                    t.Name,
                    t.FoundedYear,
                    Coach = new
                    {
                        t.Coach.Id,
                        t.Coach.Name,
                        // Adicione outras propriedades do treinador, se necessário
                    },
                    Players = t.Players.Select(p => new
                    {
                        p.Id,
                        p.Name,
                        p.Age,
                        p.Position,
                        // Adicione outras propriedades do jogador, se necessário
                    }).ToList()
                })
                .ToList();

            return Ok(teams);
        }

        [HttpGet("{id}")]
        public IActionResult GetTeamById(int id)
        {
            var team = _context.Teams
                .Where(t => t.Id == id)
                .Select(t => new
                {
                    t.Id,
                    t.Name,
                    t.FoundedYear,
                    Coach = new
                    {
                        t.Coach.Id,
                        t.Coach.Name,
                        // Adicione outras propriedades do treinador, se necessário
                    },
                    Players = t.Players.Select(p => new
                    {
                        p.Id,
                        p.Name,
                        p.Age,
                        p.Position,
                        // Adicione outras propriedades do jogador, se necessário
                    }).ToList()
                })
                .FirstOrDefault();

            if (team == null)
            {
                return NotFound();
            }

            return Ok(team);
        }

        [HttpPost]
        public IActionResult CreateTeam(Team team)
        {
            _context.Teams.Add(team);
            _context.SaveChanges();
            return CreatedAtAction(nameof(GetTeamById), new { id = team.Id }, team);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateTeam(int id, Team updatedTeam)
        {
            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }
            team.Name = updatedTeam.Name;
            team.FoundedYear = updatedTeam.FoundedYear;
            // Atualize outras propriedades conforme necessário
            _context.SaveChanges();
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteTeam(int id)
        {
            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }
            _context.Teams.Remove(team);
            _context.SaveChanges();
            return NoContent();
        }
    }
}
