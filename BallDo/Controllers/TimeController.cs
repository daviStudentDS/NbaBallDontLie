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
      /*  public async Task<IEnumerable<Team>> GetAllTeams()
        {
            var teams = await _context.Teams.Include(p => p.Players).Include(t => t.Coach).ToList();
            return teams;
        }*/

        [HttpGet("{id}")]
        public IActionResult GetTeamById(int id)
        {
            var team = _context.Teams.Select(t => new Team
            {
                Id = t.Id,
                Name = t.Name,
                Coach = t.Coach

            }).FirstOrDefault(t => t.Id == id);
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
            team.Players = updatedTeam.Players;
            team.Coach = updatedTeam.Coach;
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
